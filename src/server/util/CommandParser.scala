package server.util

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 03.10.13
 * Time: 17:06
 * Parses commands. Nuff said.
 */

//if (command.params forall(_ == params(command.params indexOf())))
case class Command(action:String, params:Array[String]) {
  override def equals(obj: scala.Any): Boolean = {
    obj.isInstanceOf[Command] && obj.asInstanceOf[Command].action.equals(action) && obj.asInstanceOf[Command].params.mkString.equals(params.mkString)
  }
}

object CommandParser {
  def parse(command:String):Command = {
    val strings  = command.split(" ")
    strings.head.charAt(0) match {
      case '/' => Command(strings.head, strings.tail)
      case _ => Command("/say", strings)
    }
  }
}
