package server

import java.net.{SocketException, Socket}
import java.io.{InputStreamReader, BufferedReader}

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
    try Stream continually(in readLine) foreach println catch { case e: SocketException => println("Client disco.. disconnect") }
  }
}
