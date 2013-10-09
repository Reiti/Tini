package server.core

import java.net.{SocketException, Socket}
import java.io._
import server.util.CommandParser
import server.commands.BaseCommand

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
  var username = "Anon"

  override def run() = {
    println("Client connected")
    try Stream continually(in readLine) foreach(CommandParser.parse(_).execute(this)) catch { case e: SocketException => {
      println("Client disco.. disconnect")
      server.clientThreadHandles remove(server.clientThreadHandles indexOf this)
      println(server.clientThreadHandles.length)
    } }
  }

  def breadCastToOthers(message:String):Unit = {
    val otherThreads = tiniServer.clientThreadHandles - this
    otherThreads foreach(_ receive message)
  }

  def log(message:String) = {
    println("LOG: " + message)
  }

  def receive(message:String) = {
    out println message
    out flush()
  }

}
