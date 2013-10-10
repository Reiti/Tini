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
      val tail: Array[String] = strings.tail
      strings.head.toLowerCase match {
        case "/say" => new Say(tail)
        case "/set" => new Set(tail)
        case "/error" => new Error(tail)
        case "/disconnect" => new Disconnect(tail)
        case "/list" => new List(tail)
        case "/whisper" => new Whisper(tail)
        case _   => new BaseCommand(strings)
      }
    } else {
      new Say(strings)
    }
  }
}
