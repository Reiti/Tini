package server.core

import java.net.{SocketException, Socket}
import java.io.{InputStreamReader, BufferedReader}
import server.util.{Command, CommandParser}

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 09:03
 * The thread responsible for one client connection.
 */
class ServerThread(socket: Socket) extends Thread("ServerThread") {
  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
  override def run() = {
    println("Client connected")
    try Stream continually(in readLine) map CommandParser.parse foreach executeCommand catch { case e: SocketException => println("Client disco.. disconnect") }
  }

  def executeCommand(com:Command):Unit = {
    println(com)
  }
}
