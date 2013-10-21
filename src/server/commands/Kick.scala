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
    if(fred.user.getPrivilegeForChannel(fred.channel).admin) {
      val userToKick = fred.channel.getThreadForUserName(params(0))
      userToKick match {
        case Some(kickFred) => {
          kickFred receive("/disconnect kick " + fred.user.name)
          kickFred disconnect()
          fred breadCastToAll "/say Server " + kickFred.user + " was kicked by " + fred.user
        }
        case None => fred.receive("/error Invalid username!")
      }
    } else
      fred.receive("/error Too little permissions.")
  }
}
