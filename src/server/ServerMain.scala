package server

import shared.Constants


/**
 * Created with IntelliJ IDEA.
 * User: NotReiti
 * Date: 02.10.13
 * Time: 13:35
 * The main used to start the server.
 */
object ServerMain {
  val port = 11952

  def main(args: Array[String]) = {
    println("こんにちは")
    new TiniServer(Constants.port) start()
    println("starting client")
  }
}
