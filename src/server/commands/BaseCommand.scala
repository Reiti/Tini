package server.commands

import server.core.ServerThread

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
  def execute(fred:ServerThread)
}

class BaseCommand(params:Array[String]) extends Command(params) {
  def ACTION = params.head
  def execute(fred:ServerThread) = {
    fred.log("unknown command: " + ACTION + " " + params.tail.mkString(" "))
  }
}
