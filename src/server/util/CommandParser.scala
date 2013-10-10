package server.util

import server.commands._
import server.commands.Command
import scala.collection.mutable

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
      val tail: Array[String] = strings.tail
      strings.head.toLowerCase match {
        case "/say" => new Say(tail)
        case "/auth" => new Authenticate(tail)
        case "/kick" => new Kick(tail)
        case "/disconnect" => new Disconnect(tail)
        case "/russianroulette" => new RussianRoulette(tail)
        case "/list" => new List(tail)
        case "/tsundere" => new Tsundere(tail)
        case "/whisper" => new Whisper(tail)
        case "/me" => new Me(tail)
        case _   => new BaseCommand(strings)
      }
    } else {
      new Say(strings)
    }
  }
}
