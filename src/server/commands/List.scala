package server.commands

import server.core.ServerThread
import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 10.10.13
 * Time: 09:17
 * Represents the command "/list".
 */
class List(params:Array[String]) extends Command(params){
  def ACTION: String = "/list"

  def execute(fred: ServerThread) {
    if(params.length==0)
      userList(fred)
    else
      params(0) match {
        case "users" => userList(fred)
        case _ => userList(fred)
      }
  }

  def userList(fred: ServerThread):Unit = {
    val threads = fred.channel.clientThreadHandles
    var list:ArrayBuffer[String] = new ArrayBuffer[String]()
    for(thread <- threads)
      list += thread.user.toString
    fred.receive("/list UserList: "+ list.mkString(" "))
  }
}
