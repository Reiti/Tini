package server.util

import server.commands.{Command, Say, BaseCommand}

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 03.10.13
 * Time: 17:06
 * Parses commands. Nuff said.
 */

object CommandParser {
  def parse(command:String):Command = {
    val strings  = command.split(" ")
    if(strings.head.charAt(0) == '/') {
      strings.head match {
        case "/say" => new Say(strings.tail)
        case _   => new BaseCommand(strings)
      }
    } else {
      new Say(strings)
    }
  }
}
