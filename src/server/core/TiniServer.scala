package server.core

import java.net.ServerSocket

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 08:48
 * The one listener of Tini to bind them all.
 */
class TiniServer(port: Integer) extends Thread("TiniServer") {
  override def run() = {
    val listener = new ServerSocket(port)
    println("TiniServer started listening on port " + port)
    while (true)
      new ServerThread(listener accept) start()
  }
}
