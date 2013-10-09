package client.core.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 09:57
 * To change this template use File | Settings | File Templates.
 */
class Say(params:Array[String]) extends Command(params){
  def ACTION: Any = "/say"

  def execute(client: TiniClient) {
    client.receive(params.mkString(" "))
  }
}
