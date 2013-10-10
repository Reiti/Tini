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
    val userToKick = fred.channel.getThreadForUserName(params(0))
    userToKick match {
      case Some(user) => {
        user receive("/disconnect kick " + fred.username)
        user disconnect()
        fred breadCastToAll "/say Server " + user.username +" was kicked by " + fred.username
      }
      case None => fred.receive("/error Invalid username!")
    }
  }
}
