package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 10.10.13
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 */
class Tsundere(params:Array[String]) extends Command(params) {
  def ACTION: String = "/tsundere"

  def execute(fred: ServerThread) {
    if(params.length>0) {
      val firstWord = params(0)
      val lastWord = params(params.length-1)
      val lastChar = lastWord.charAt(lastWord.length()-1)
      val firstChar = params(0).charAt(0)
      val paramString = params.mkString(" ")
      var tsun = ""
      lastChar match {
        case '?' => tsun = firstChar+"-"+firstChar+"-"+paramString+" " + "Baka!"
        case '!' => tsun = firstChar+"-"+paramString+" " + "Baka!"
        case _ => tsun = firstChar+"-"+paramString+", baka!"
      }
      new Say(Array(tsun)).execute(fred)
    }
  }
}
