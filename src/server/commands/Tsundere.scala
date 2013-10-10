package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 10.10.13
 * Time: 10:05
 * The class enabling you to go /tsundere.
 */
class Tsundere(params:Array[String]) extends Command(params) {
  def ACTION: String = "/tsundere"

  def execute(fred: ServerThread) {
    if(params.length>0) {
      toggleTsundere(fred)
      new Say(params).execute(fred)
      toggleTsundere(fred)
    } else toggleTsundere(fred)
  }

  def toTsundere = {
    val lastWord = params(params.length-1)
    val lastChar = lastWord.charAt(lastWord.length()-1)
    val firstChar = params(0).charAt(0)
    val paramString = params.mkString(" ")
    lastChar match {
      case '?' => firstChar+"-"+firstChar+"-"+paramString+" " + "Baka!"
      case '!' => firstChar+"-"+paramString+" " + "Baka!"
      case _ => firstChar+"-"+paramString+", baka!"
    }
  }

  def toggleTsundere(fred: ServerThread) = {
    fred.tsundere = !fred.tsundere
  }
}
