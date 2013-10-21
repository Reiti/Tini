package server.core

import java.net.ServerSocket
import scala.collection.mutable._
import shared.Constants

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 08:48
 * The one listener of Tini to bind them all.
 */
class TiniServer(port: Integer) extends Thread("TiniServer") {

  var channelList: ListBuffer[Channel] = new ListBuffer[Channel]
  var userList: ListBuffer[User] = new ListBuffer[User]

  override def run() = {
    channelList += Channel(Constants.standardChannel, this)
    userList += User("Giymo11", Array((Constants.standardChannel, Privilege(read = true, write = true, admin = true))))
    userList += User("Reiti", Array((Constants.standardChannel, Privilege(read = true, write = true, admin = true))))
    val listener = new ServerSocket(port)
    println("TiniServer started listening on port " + port)
    while (true) {
      val thread = new ServerThread(listener accept, channelList(0))
      thread start()
    }
  }

  def getUser(name:String): User = {
    if(userList.forall(_.name != name))
      userList += User(name, Array((Constants.standardChannel, Constants.standardPrivilege)))
    userList(userList indexWhere (_.name == name))
  }
}
