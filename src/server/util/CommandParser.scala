package server.util

import server.commands._
import server.commands.Command

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

    if(strings.head.length() == 0)
       return new BaseCommand(strings)

    if(strings.head.charAt(0) == '/') {
      val tail: Array[String] = strings.tail
      strings.head.toLowerCase match {
        case "/say" => new Say(tail)
        case "/auth" => new Authenticate(tail)
        case "/kick" => new Kick(tail)
        case "/disconnect" => new Disconnect(tail)
        case "/russianroulette" => new RussianRoulette(tail)
        case "/list" => new List(tail)
        case "/tsundere" => new Tsundere(tail)
        case _   => new BaseCommand(strings)
      }
    } else {
      new Say(strings)
    }
  }
}
