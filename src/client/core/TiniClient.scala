package client.core

import java.net.Socket
import java.io.{BufferedReader, PrintWriter, OutputStreamWriter, InputStreamReader}
import client.core.util.CommandParser

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 09:15
 * A client that can send stuff to the server.
 */
class TiniClient(address: String, port: Integer) {

  def this(port: Integer) = this("localhost", port)

  var username = "Anon"
  var prompt = username + ">"
  val socket = new Socket(address, port)
  val out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream))
  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))

  val receiveThread = new Thread("receiveThread") {
    override def run() = Stream.continually(in.readLine()).map(CommandParser.parse).foreach(_.execute(TiniClient.this))
  }.start()

  val inputThread = new Thread("inputThread") {
    override def run() = Stream.continually(readLine(prompt)).takeWhile(_ != null).foreach(send)
  }.start()

  def send(message: String) = {
    out println message
    out flush()
  }

  def log(message: String) {
    System.out.print("\r"+message+"\n"+prompt)
  }

  def receive(message: String) = print("\r"+message+"\n"+ prompt)
}
