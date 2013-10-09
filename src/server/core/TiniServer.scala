package server.core

import java.net.ServerSocket
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
  var anonNumber:Integer = -1

  override def run() = {
    val listener = new ServerSocket(port)
    println("TiniServer started listening on port " + port)
    while (true) {
      val thread = new ServerThread(listener accept, this)
      clientThreadHandles += thread
      thread start()
    }
  }

  def usernameAvailable(username:String):Boolean = {
    for(fured <- clientThreadHandles) {
      println(fured.username + " " + username)
      if(fured.username.equals(username))
        return false
    }
    true
  }

  def nextAnon():String = {
    anonNumber += 1
    anonNumber.toString
  }
}
