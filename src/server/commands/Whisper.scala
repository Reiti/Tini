package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 10.10.13
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
class Whisper(params:Array[String]) extends Command(params){
  def ACTION: String = "/whisper"

  def execute(fred: ServerThread) {
    if(params.length > 1) {
      val user = fred.server.getThreadForUserName(params(0))
      user match {
        case Some(x) => x.receive("/whisper " + fred.username + " " + params.tail.mkString(" "))
        case None => fred.receive("/error Invalid username!")
      }
    }
  }
}
