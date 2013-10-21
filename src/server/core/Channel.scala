package server.core

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable
import shared.Constants

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 10.10.13
 * Time: 15:00
 * The class representing a channel.
 */
case class Channel(name: String, channelManager: TiniServer) {

  var clientThreadHandles:ArrayBuffer[ServerThread] = new ArrayBuffer[ServerThread] with mutable.SynchronizedBuffer[ServerThread]
  var anonNumber:Integer = -1

  def usernameAvailable(username:String):Boolean = clientThreadHandles.forall(_.user.name != username)

  def nextAnon():Integer = {
    anonNumber+=1
    anonNumber
  }

  def getThreadForUserName(username:String):Option[ServerThread] = {
    for(thread <- clientThreadHandles) {
      if(thread.user.name equals username)
        return Some(thread)
    }
    None
  }

  def getPrivilegeFor(username:String): Privilege = {
    Constants.standardPrivilege
  }

  def connect(fred: ServerThread) {
    fred.channel.clientThreadHandles += fred
    fred.breadCastToOthers("/me " + fred.user + " connected to this channel (" + fred.channel.name + ").")
    fred.receive("/say Server You connected to channel " + fred.channel.name + ".")
  }

  def disconnect(fred: ServerThread) {
    fred.channel.clientThreadHandles -= fred
    fred.breadCastToAll("/me " + fred.user + " disconnected from this channel.")
  }
}
