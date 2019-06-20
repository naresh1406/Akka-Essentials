package part1recap

import scala.concurrent.Future
import scala.util._

/**
  * Created by ngupta on 2019-06-19.
  */
object MultithreadingRecap extends App {

  val aThread = new Thread(() => println("I'm running in parallel"))
  aThread.start()
  aThread.join()

  val threadHello = new Thread(() => (1 to 1000).foreach(_ => println("Hello")))
  val threadGoodBye = new Thread(() => (1 to 1000).foreach(_ => println("GoodBye")))

  //  threadHello.start()
  //  threadGoodBye.start()

  import scala.concurrent.ExecutionContext.Implicits.global

  val future = Future {
    // long computation on different threads
    42
  }

  //callback
  future.onComplete {
    case Success(42) => println("Success on 42")
    case Failure(_) => println("Whatever failure is failure")
  }

  val aProcessedFuture = future.map(_ + 1) //future with 43
  val aFlatFuture = future.flatMap { value =>
    Future(value + 2)
  } // Future(44)

  val aFilteredFuture = future.filter(_ % 2 == 0) // NoSuchElementException

  val aNonSenseFuture = for {
    meaningOfLife <- future
    filteredMeaning <- aFilteredFuture
  } yield meaningOfLife + filteredMeaning


  // andThen, recover, recoverWith

  // Promises


}

