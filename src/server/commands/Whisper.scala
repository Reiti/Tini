package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 10.10.13
 * Time: 11:56
 * The command used to whisper another user.
 */
class Whisper(params:Array[String]) extends Command(params){
  def ACTION: String = "/whisper"

  def execute(fred: ServerThread) {
    if(params.length > 1) {
      val user = fred.server.getThreadForUserName(params(0))
      user match {
        case Some(x) => {
          var str: String = null
          if(fred.tsundere)
            str = new Tsundere(params.tail).toTsundere
          else
            str = params.tail.mkString(" ")
          x.receive("/whisper " + fred.username + " " + str)
        }
        case None => fred.receive("/error Invalid username!")
      }
    }
  }
}
