import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

case class Person(
  id: UUID,
  firstName: String,
  lastName: String,
  occupation: String
)

/** Asynchronous data store simiulating retrieving data over HTTP. */ 
object PersonStore {

  // JS is single-threaded so global ec is fine for all purposes
  implicit val ec = ExecutionContext.global

  // We can generate UUIDs on the client-side!
  private val johnHughes = Person(UUID.randomUUID, "John", "Hughes", "computer scientiest")
  private val donaldKnuth = Person(UUID.randomUUID, "Donald", "Knuth", "computer scientiest")

  private var people: Map[UUID, Person] = Map(
    johnHughes.id -> johnHughes,
    donaldKnuth.id -> donaldKnuth
  )

  def add(p: Person): Future[Unit] = Future {
    people = people + (p.id -> p)
    ()
  }

  def delete(id: UUID): Future[Unit] = Future {
    people - id
  }

  def getAll: Future[List[Person]] = Future {
    people.values.toList
  }
}