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
      channelList(0).clientThreadHandles += thread
      thread start()
    }
  }

  def changeChannel(fred:ServerThread, channelName:String) = {
    if(channelList.forall(_.name != channelName))
      channelList += Channel(channelName, this)

    val channel = channelList(channelList.indexWhere(_.name == channelName))
    val socket = fred.socket
    fred disconnect()
    val thread = new ServerThread(socket, channel)
    channel.clientThreadHandles += thread
    thread start()
  }
}
