package client.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 09:57
 * The command sent from the server, representing a message from another User.
 */
class Say(params:Array[String]) extends Command(params){
  def ACTION: String = "/say"

  def execute(client: TiniClient) {
    client.receive(params.head + ": " + params.tail.mkString(" "))
  }
}
