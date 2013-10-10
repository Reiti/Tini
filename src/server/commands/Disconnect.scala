package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 17:04
 * The class representing the /disconnect of the client.
 */
class Disconnect(params:Array[String]) extends Command(params){
  def ACTION: String = "/disconnect"

  def execute(fred: ServerThread) {
    fred breadCastToOthers "/say Server " + fred.username + " disconnected."
    fred disconnect()
    fred receive "/disconnect server"
  }
}
