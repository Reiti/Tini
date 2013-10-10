package client.commands

import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 10.10.13
 * Time: 11:58
 * To change this template use File | Settings | File Templates.
 */
class Whisper(params:Array[String]) extends Command(params) {
  def ACTION: String = "/whisper"

  def execute(client: TiniClient) {
    client.receive(params(0) + " whispers: " + params.tail.mkString(" "))
  }
}
