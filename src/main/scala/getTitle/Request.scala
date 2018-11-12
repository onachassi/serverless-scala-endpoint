package getTitle

import scala.beans.BeanProperty
import org.tsers.zeison.Zeison

class Request(@BeanProperty var body: String = "samplebody") {
  def this() = this(body="samplebody")

  def url() : String = {
  	val url = Zeison.parse(body).url.toStr
  	return url
  }
}
