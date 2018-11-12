package getTitle

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import scala.collection.JavaConverters
import org.tsers.zeison.Zeison
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model._

class ApiGatewayHandler extends RequestHandler[Request, ApiGatewayResponse] {

	def getElements(url: String) : Map[String, Any] = {
		val document = JsoupBrowser().get(url)
		val title = document >> text("title")
		val body = document >> text("body")
		return Map(
		  "title"  -> title,
		  "body"   -> body
		)
	}

  def handleRequest(input: Request, context: Context): ApiGatewayResponse = { 
  	val elements = getElements("http://www."+input.url)

  	val response = Zeison.render(Zeison.toJson(elements))

    val headers = Map("Access-Control-Allow-Origin" -> "*")
    ApiGatewayResponse(200, response,
      JavaConverters.mapAsJavaMap[String, Object](headers),
      true)
  }
}
