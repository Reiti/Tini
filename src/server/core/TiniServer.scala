package server.core

import java.net.ServerSocket
import scala.collection.mutable._

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 08:48
 * The one listener of Tini to bind them all.
 */
class TiniServer(port: Integer) extends Thread("TiniServer") {

  var channelList: ListBuffer[Channel] = new ListBuffer[Channel]

  override def run() = {
    channelList += Channel("default", this)
    val listener = new ServerSocket(port)
    println("TiniServer started listening on port " + port)
    while (true) {
      val thread = new ServerThread(listener accept, channelList(0))
      thread start()
    }
  }
}
