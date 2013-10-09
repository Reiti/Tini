package client.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 09.10.13
 * Time: 10:33
 * The Command the server sends to represent an error.
 */
class Error(params:Array[String]) extends Command(params){
  def ACTION: String = "/error"

  def execute(client: TiniClient) {
    client.log("Error: " + params.mkString(" "))
  }
}
