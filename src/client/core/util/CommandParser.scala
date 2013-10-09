package client.core.util

import client.core.commands._
/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 09:56
 * To change this template use File | Settings | File Templates.
 */
object CommandParser {
  def parse(command:String):Command = {
    val strings  = command.split(" ")
    if(strings.head.charAt(0) == '/') {
      strings.head match {
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
