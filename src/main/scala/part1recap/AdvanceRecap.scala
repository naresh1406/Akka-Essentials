package part1recap

import scala.concurrent.Future

/**
  * Created by ngupta on 2019-06-18.
  */
object AdvanceRecap extends App {

  val partialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 65
    case 5 => 345
  }

  val pf = (x: Int) => x match {
    case 1 => 42
    case 2 => 65
    case 5 => 345
  }

  val lifted = partialFunction.lift
  println(lifted(2))

  val pfChain = partialFunction.orElse[Int, Int] {
    case 60 => 9000
  }

  //implicit
  implicit val timeout = 3000

  def setTimeout(f: () => Unit)(implicit timeout: Int) = f()

  setTimeout(() => println("timeout"))


  implicit val inverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  println(List(1, 2, 3, 4).sorted)

  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    println("Hello Future")
  }


}
