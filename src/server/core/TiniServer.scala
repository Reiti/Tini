package server.core

import java.net.ServerSocket
import scala.collection.mutable._
import scala.collection.mutable
import java.util

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 08:48
 * The one listener of Tini to bind them all.
 */
class TiniServer(port: Integer) extends Thread("TiniServer") {

  var channelList = new util.ArrayList[Channel]

  override def run() = {
    channelList.add(new Channel("default"))
    val listener = new ServerSocket(port)
    println("TiniServer started listening on port " + port)
    while (true) {
      val thread = new ServerThread(listener accept, channelList.get(0))
      channelList.get(0).clientThreadHandles += thread
      thread start()
    }
  }


}
