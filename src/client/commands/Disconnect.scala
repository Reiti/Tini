package client.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 16:33
 * The class representing the /disconnect received by the server.
 */
class Disconnect(params:Array[String]) extends Command(params) {
  def ACTION: String = "/disconnect"

  def execute(client: TiniClient) {
    params(0) match{
      case "kick" => client.error("Kicked by: " + params(1))
      case _ => client.error("You disconnected.")
    }
    client.socket.close()
    client.in.close()
    client.out.close()
    client.end = true
  }
}
