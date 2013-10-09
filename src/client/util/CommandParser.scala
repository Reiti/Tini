package client.util

import client.commands._
/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 09:56
 * Parses the Server-to-Client commands.
 */
object CommandParser {
  def parse(command:String):Command = {
    val strings  = command.split(" ")
    if(strings.head.charAt(0) == '/') {
      strings.head.toLowerCase match {
        case "/say" => new Say(strings.tail)
        case "/set" => new Set(strings.tail)
        case "/error" => new Error(strings.tail)
        case _   => new BaseCommand(strings)
      }
    } else {
      new Say(strings)
    }
  }
}
