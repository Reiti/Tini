package server.commands

import server.core.ServerThread
import scala.None

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 16:21
 * The class representing the /kick command.
 */
class Kick(params:Array[String]) extends Command(params){
  def ACTION: String = "/kick"

  def execute(fred: ServerThread) {
    val userToKick = fred.server.getThreadForUserName(params(0))
    userToKick match {
      case Some(user) => {
        user removeSelf()
        user receive("/disconnect kick "+fred.username)
        fred breadCastToAll "/say Server " + user.username +" was kicked by " + fred.username
        //user kick()
      }
      case None => fred.receive("/error Invalid username!")
    }
  }
}
