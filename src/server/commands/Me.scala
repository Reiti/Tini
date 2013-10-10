package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: Raffy23
 * Date: 10.10.13
 * Time: 12:11
 * The class representing the /me command.
 */
class Me(params:Array[String]) extends Command(params){
  def ACTION: String = "/me"

  def execute(fred: ServerThread) {
    fred breadCastToOthers ACTION + " " + fred.username + " " + params.mkString(" ")
  }
}
