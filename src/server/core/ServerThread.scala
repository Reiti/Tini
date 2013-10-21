package server.core

import java.net.Socket
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
case class ServerThread(socket: Socket, var channel:Channel) extends Thread("ServerThread") {
  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
  val out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream))
  var user = channel.channelManager.getUser(Constants.standardUsername)
  var tsundere = false

  override def run() = {
    new Authenticate(("Anon" + channel.nextAnon() + " ").split(" ")).execute(ServerThread.this)
    channel.connect(this)
    try Stream continually(in readLine) takeWhile(_ != null) foreach(CommandParser.parse(_).execute(this)) catch { case e:Exception => {
      log(e.getMessage)
      disconnect()
    } }
  }

  def breadCastToOthers(message:String):Unit = (channel.clientThreadHandles - this) foreach(_ receive message)

  def breadCastToAll(message:String) = channel.clientThreadHandles foreach(_ receive message)

  def log(message:String) = println("LOG: " + message)

  def receive(message:String) = {
    try {
      out println message
      out flush()
    } catch { case e:Exception => disconnect() }
  }

  def disconnect():Unit = {
    println("Client disco.. disconnect")
    channel.disconnect(this)
    receive("/disconnect server")
    try {
      in.close()
      out.close()
      socket.close()
    } catch { case e:Exception => log(e.getMessage) }
  }
}
