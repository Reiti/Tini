package client.core.commands


import client.core.TiniClient

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 08.10.13
 * Time: 16:05
 * The abstract class representing a Command.
 */
abstract case class Command(params:Array[String]) {
  override def equals(obj: scala.Any): Boolean = {
    obj.isInstanceOf[Command] && obj.asInstanceOf[Command].ACTION.equals(ACTION) && obj.asInstanceOf[Command].params.mkString.equals(params.mkString)
  }
  def ACTION:Any
  def execute(client:TiniClient)
}

class BaseCommand(params:Array[String]) extends Command(params) {
  def ACTION = params.head
  def execute(client:TiniClient) = {
    client.log("Unknown action from server: " + params.head)
  }
}
