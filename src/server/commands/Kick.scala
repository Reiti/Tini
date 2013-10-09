package server.commands

import server.core.ServerThread
import scala.None

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
class Kick(params:Array[String]) extends Command(params){
  def ACTION: String = "/kick"

  def execute(fred: ServerThread) {
    val userToKick = fred.server.getThreadByUserName(params(0))
    userToKick match {
      case Some(user) => {
        user receive("/disconnect kick "+fred.username)
        fred breadCastToOthers "/say " + user.username +" was kicked by " + fred.username
        user kick()
      }
      case None => fred.receive("/error Invalid username!")
    }
  }
}
