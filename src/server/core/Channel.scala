package server.core

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable

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

  def usernameAvailable(username:String):Boolean = {
    for(fured <- clientThreadHandles) {
      if(fured.username.equals(username))
        return false
    }
    true
  }

  def nextAnon():String = {
    anonNumber += 1
    anonNumber.toString
  }

  def getThreadForUserName(username:String):Option[ServerThread] = {
    for(thread <- clientThreadHandles) {
      if(thread.username equals username)
        return Some(thread)
    }
    None
  }
}
