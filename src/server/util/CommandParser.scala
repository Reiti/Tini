package server.util

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 03.10.13
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */

case class Command(action:String, params:Array[String])

object CommandParser {
  def parse(command:String):Command = {
    val strings  = command.split(" ")
    strings.head.charAt(0) match {
      case '/' => Command(strings.head, strings.tail)
      case _ => Command("/say", strings)
    }
  }
}
