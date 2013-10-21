package server.core


case class Privilege(read:Boolean, write:Boolean, admin:Boolean) {}

case class User(name:String, privileges:Array[(String, Privilege)]) {
  def getPrivilegeForChannel(channel: Channel): Privilege = {
    if(!privileges.forall(_._1 != channel.name))
      privileges(privileges.indexWhere(_._1 == channel.name))._2
    else {
      channel.getPrivilegeFor(name)
    }
  }

  override def toString:String = {
    var str = name + ", privs: "
    privileges.foreach(str += getChannelPrivilege(_))
    str
  }

  private def getChannelPrivilege(tuple:(String, Privilege)) = {
    "Channel: " + tuple._1 + ", " + tuple._2
  }
}
