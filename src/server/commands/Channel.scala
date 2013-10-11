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
      channelList += Channel(channelName, fred.channel.channelManager)

    fred.channel.clientThreadHandles -= fred
    fred.breadCastToAll("/me " + fred.username + " disconnected from this channel.")
    fred.channel = channelList(channelList.indexWhere(_.name == channelName))
    fred.channel.clientThreadHandles += fred
    fred.breadCastToOthers("/me " + fred.username + " connected to this channel (" + fred.channel.name + ").")
    fred.receive("/say Server You connected to channel " + fred.channel.name + ".")
  }
}
