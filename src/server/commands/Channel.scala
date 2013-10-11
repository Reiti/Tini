package server.commands

import server.core.ServerThread

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 10.10.13
 * Time: 15:39
 * The class representing the command "/channel" to change channel.
 */
class Channel(params:Array[String]) extends Command(params) {
  def ACTION: String = "/channel"

  def execute(fred: ServerThread) {
    fred.channel.channelManager.changeChannel(fred, params(0))
  }
}
