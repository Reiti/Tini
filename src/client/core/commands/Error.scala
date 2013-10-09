package client.core.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
class Error(params:Array[String]) extends Command(params){
  def ACTION: Any = "/error"

  def execute(client: TiniClient) {
    client.log("Error: " + params.mkString(" "))
  }
}
