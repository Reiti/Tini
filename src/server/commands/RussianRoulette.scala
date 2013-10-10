package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 10.10.13
 * Time: 08:18
 * The class representing the command "/russianroulette".
 */
class RussianRoulette(params:Array[String]) extends Command(params) {
  def ACTION: String = "/russianroulette"

  def execute(fred: ServerThread) {
    val random:Integer = (Math.random() * 6.0).toInt
    println("Random: "+ random)
    if(random == 0) {
      fred.breadCastToAll("/me " + fred.username + " played russian roulette and shot himself.")
      new Disconnect(null).execute(fred)
    } else
      fred.breadCastToAll("/me " + fred.username + " played russian roulette and survived.")
  }
}
