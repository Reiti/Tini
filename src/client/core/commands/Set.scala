package client.core.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 09:27
 * To change this template use File | Settings | File Templates.
 */
class Set(params:Array[String]) extends Command(params){
  def ACTION: Any = "/set"

  def execute(client: TiniClient) {
    params.head match {
      case "username" => {
        client.username = params(1)
        client.prompt = params(1)+">"
      }
    }
  }
}

