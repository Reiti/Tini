package client.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 09:27
 * The command used to set parameters at the Client.
 */
class Set(params:Array[String]) extends Command(params){
  def ACTION: String = "/set"

  def execute(client: TiniClient) {
    params.head match {
      case "username" => {
        client.username = params(1)
        client.prompt = params(1)+"> "
        print("\r" + client.prompt)
      }
    }
  }
}

