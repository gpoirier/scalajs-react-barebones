import japgolly.scalajs.react.React
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import scala.scalajs.js._
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

object Main {
  def main(): Unit = {
    // Attach our react component into the DOM
    // This is the real DOM, not the Virtual DOM
    val root = dom.document.getElementById("root")
    React.StrictMode(App.component()).renderIntoDOM(root)
    ()
  }
}
