package server.core

import java.net.{SocketException, Socket}
import java.io._
import server.util.CommandParser
import server.util.Command

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 09:03
 * The thread responsible for one client connection.
 */
class ServerThread(socket: Socket, tiniServer:TiniServer) extends Thread("ServerThread") {
  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
  val out = new PrintWriter (new OutputStreamWriter(socket.getOutputStream))
  val server = tiniServer
  override def run() = {
    println("Client connected")
    try Stream continually(in readLine) map CommandParser.parse foreach broadCast catch { case e: SocketException => {
      println("Client disco.. disconnect")
      server.clientThreadHandles remove(server.clientThreadHandles indexOf this)
      println(server.clientThreadHandles.length)
    } }
  }

  def broadCast(com:Command):Unit = {
    val otherThreads = tiniServer.clientThreadHandles - this
    otherThreads foreach(_.executeCommand(com))
  }

  def executeCommand(com:Command):Unit = {
    println("Executing in:" + tiniServer.clientThreadHandles.indexOf(this))
    com.action match {
      case "/say" => {
        if (com != null && com.params != null) {
          out println com.params.mkString(" ")
          out flush()
        }
      }
      case _ => println(com.action)
    }
  }
}
