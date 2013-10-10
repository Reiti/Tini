package client.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 10.10.13
 * Time: 09:38
 * To change this template use File | Settings | File Templates.
 */
class List(params:Array[String]) extends Command(params){
  def ACTION: String = "/list"

  def execute(client: TiniClient) {
    client.receive(params.mkString("\n"))
  }
}
