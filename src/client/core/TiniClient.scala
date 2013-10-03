package client.core

import java.net.Socket
import java.io.{BufferedReader, PrintWriter, OutputStreamWriter, InputStreamReader}

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 09:15
 * A client that can send stuff to the server.
 */
class TiniClient(address: String, port: Integer) {
  def this(port: Integer) = this("localhost", port)

  val socket = new Socket(address, port)
  val out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream))
  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))

  def send(message: String) = {
    out println message
    out flush()
  }
}
