package server.commands

import server.core.{Channel, ServerThread}

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
    val channelList = fred.channel.channelManager.channelList
    val channelName = params(0)

    if(channelList.forall(_.name != channelName))
      channelList += server.core.Channel(channelName, fred.channel.channelManager)

    fred.channel.disconnect(fred)
    fred.channel = channelList(channelList.indexWhere(_.name == channelName))
    fred.channel.connect(fred)
  }
}
