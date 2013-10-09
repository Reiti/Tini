package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 08.10.13
 * Time: 15:04
 * The class representing the command "/say".
 */
class Say(params:Array[String]) extends Command(params) {
  def ACTION = "/say"
  def execute(fred:ServerThread) {
    fred breadCastToOthers fred.username+": " + params.mkString(" ")
  }
}
