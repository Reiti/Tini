package client.core

import shared.Constants

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 10:41
 * The main used to start the client.
 */
object ClientMain {
  val port = 11952

  def main(args: Array[String]) ={
    val client = new TiniClient(Constants.port)
    while(true) {
      val input = readLine("Input> ")
      client send input
    }
  }
}