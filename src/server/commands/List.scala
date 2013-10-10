package server.commands

import server.core.ServerThread
import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 10.10.13
 * Time: 09:17
 * To change this template use File | Settings | File Templates.
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
    val threads = fred.server.clientThreadHandles
    var list:ArrayBuffer[String] = new ArrayBuffer[String]()
    for(thread <- threads) {
      list += thread.username
    }

    fred.receive("/list Userlist: "+ list.mkString(" "))

  }
}
