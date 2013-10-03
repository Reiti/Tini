package server.core

import java.net.ServerSocket
import java.util
import scala.collection.mutable._
import scala.collection.mutable

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 08:48
 * The one listener of Tini to bind them all.
 */
class TiniServer(port: Integer) extends Thread("TiniServer") {
  var clientThreadHandles:ArrayBuffer[ServerThread] = new ArrayBuffer[ServerThread] with mutable.SynchronizedBuffer[ServerThread]
  override def run() = {
    val listener = new ServerSocket(port)
    println("TiniServer started listening on port " + port)
    while (true) {
      val thread = new ServerThread(listener accept)
      clientThreadHandles += thread
      thread start()
    }
  }
}
