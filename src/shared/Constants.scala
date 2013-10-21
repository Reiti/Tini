package shared

import server.core.Privilege

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 03.10.13
 * Time: 10:42
 * Defines shared constants.
 */
object Constants {
  def port = 11952
  def standardUsername = "Anon"
  def standardChannel = "default"
  def standardPrivilege = Privilege(read = true, write = true, admin = false)
}
