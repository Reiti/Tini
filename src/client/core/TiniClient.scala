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

  val prompt = "Input> "
  val socket = new Socket(address, port)
  val out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream))
  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))

  val receiveThread = new Thread("receiveThread") {
    override def run() = {
      while(true)
        receive(in.readLine)
    }
  }.start()
  val inputThread = new Thread("inputThread") {
    override def run() = {
      while(true)
        send(readLine(prompt))
    }
  }.start()

  def send(message: String) = {
    out println message
    out flush()
  }

  def receive(message: String) = print("\r" + "Output> " + message + "\n" + prompt)
}
