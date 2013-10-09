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

  def main(args: Array[String]):Unit = {
    val ip = readLine("Server IP: ")
    println()
    if(ip equals "")
      new TiniClient(Constants.port)
    else
      new TiniClient(ip,Constants.port)
  }
}
