import japgolly.scalajs.react.React
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import scala.concurrent.{ExecutionContext, Future}

object App{
  // Creates a component with no properties (Unit) and a List[Person] as state
  class Backend(scope: BackendScope[Unit, List[Person]]) {
    // JS is single-threaded so global EC is fine for all purposes
    private implicit val ec = ExecutionContext.global
    
    // Retrieve the list of people and modify the component's state with new list
    // By modifying the State, we're forcing the render function to get called
    private def loadPeople: Callback = 
      // Async operations have to be wrapped in Callback.future
      Callback.future(
        PersonStore.getAll.map { people: List[Person] =>
          scope.setState(people)
        })

    // Our render function gets called any time our props or state change
    // This component has no props (Unit)
    def render(s: List[Person]): VdomElement = 
      <.div(
        <.h1("Hello NE Scala"),
        <.button("Load People", ^.`type` := "button", ^.onClick --> loadPeople),
        <.ul(
          s.toTagMod { person => 
            <.li(s"${person.firstName} ${person.lastName}")
          }
        ).when(s.nonEmpty)
      )
  }

  val component = ScalaComponent.builder[Unit]("App")
    .initialState(List.empty[Person])
    .renderBackend[Backend]
    .build

}