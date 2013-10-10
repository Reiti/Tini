package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 08.10.13
 * Time: 15:04
 * The class representing the command "/say".
 */
class Say(params:Array[String]) extends Command(params) {
  def ACTION = "/say"
  def execute(fred:ServerThread) {
    if(params == null || params.length == 0 || params(0)=="")
      return

    var str: String = ""

    if(fred.tsundere)
      str = new Tsundere(params).toTsundere
    else
      str = params.mkString(" ")

    fred breadCastToOthers "/say" + " " + fred.username + " " + str
  }
}
