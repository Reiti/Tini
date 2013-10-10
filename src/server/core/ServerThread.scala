package server.core

import java.net.{SocketException, Socket}
import java.io._
import server.util.CommandParser
import server.commands.Authenticate
import shared.Constants

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 09:03
 * The thread responsible for one client connection.
 */
class ServerThread(socket: Socket, tiniServer:TiniServer) extends Thread("ServerThread") {
  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
  val out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream))
  val server = tiniServer
  var username = Constants.standardUsername
  var tsundere = false

  override def run() = {
    new Authenticate(("Anon" + tiniServer.nextAnon() + " ").split(" ")).execute(ServerThread.this)
    try Stream continually(in readLine) takeWhile(_ != null) foreach(CommandParser.parse(_).execute(this)) catch { case e:Exception => disconnect() }
  }

  def breadCastToOthers(message:String):Unit = {
    val otherThreads = tiniServer.clientThreadHandles - this
    otherThreads foreach(_ receive message)
  }

  def breadCastToAll(message:String) = tiniServer.clientThreadHandles foreach(_ receive message)

  def log(message:String) = {
    println("LOG: " + message)
  }

  def receive(message:String) = {
    try {
      out println message
      out flush()
    } catch { case e:Exception => disconnect() }
  }

  def disconnect() = {
    println("Client disco.. disconnect")
    server.clientThreadHandles remove(server.clientThreadHandles indexOf this)
  }
}
