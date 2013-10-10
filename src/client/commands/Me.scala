package client.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: Raffy23
 * Date: 10.10.13
 * Time: 12:24
 * The class representing the /me command.
 */
class Me (params:Array[String]) extends Command(params) {
  def ACTION: String = "/me"

  def execute(client: TiniClient) {
    client.receive(params.head + " " + params.tail.mkString(" "))
  }
}
