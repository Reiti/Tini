package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 09:39
 * To change this template use File | Settings | File Templates.
 */
class Authenticate(params:Array[String]) extends Command(params){
  def ACTION: Any = "/auth"

  def execute(fred: ServerThread) {
    println(fred.server.usernameAvailable(params(0)))
    if(fred.server.usernameAvailable(params(0))) {
      fred.receive("/set username " + params(0))
      fred.username = params(0)
      return
    }
    fred.receive("/error Username:"+ params(0)+" not available!")
  }
}
