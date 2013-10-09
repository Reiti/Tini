package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
class Disconnect(params:Array[String]) extends Command(params){
  def ACTION: String = "/disconnect"

  def execute(fred: ServerThread) {
    fred breadCastToOthers "/say " + fred.username +" disconnected"
    fred receive "/disconnect server"
    fred kick()
  }
}
