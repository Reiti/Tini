package server.commands

import server.core.ServerThread
import shared.Constants

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 09:39
 * The command representing a authentication try from the client.
 */
class Authenticate(params:Array[String]) extends Command(params){
  def ACTION = "/auth"

  def execute(fred: ServerThread) {
    if(fred.channel.usernameAvailable(params(0))) {
      fred.receive("/set username " + params(0))
      if(fred.user.name != Constants.standardUsername)
        fred.breadCastToOthers("/say Server " + fred.user + " changed his username to " + fred.channel.channelManager.getUser(params(0)))
      fred.user = fred.channel.channelManager.getUser(params(0))
      return
    }
    fred.receive("/error Username: " + params(0) + " not available!")
  }
}
