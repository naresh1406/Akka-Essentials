package part2actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import part2actors.ActorIntro.Person.LiveTheLife

object ActorIntro extends App {

  //part1 - actor sys
  val actorSystem = ActorSystem("firstActorSystem")
  println(actorSystem.name)


  //part2 - create actor
  class WordCountActor extends Actor {
    var totalWords = 0;

    def receive: PartialFunction[Any, Unit] = {
      case message: String =>
        println(s"[word counter] I have received: $message")
        totalWords += message.split(" ").length

      case msg => println(s"[word counter] I cannot understand ${msg.toString}")
    }
  }

  //part3 - initiation of actor
  val wordCounter = actorSystem.actorOf(Props[WordCountActor], "wordCounter")
  val anotherWordCounter = actorSystem.actorOf(Props[WordCountActor], "anotherWordCounter")

  //part4 - communicate
  wordCounter ! "I am learning Akka and its pretty cool!" // ! --> tell
  anotherWordCounter ! "I different message"

  /*object Person {
    def props(name: String) = Props(new Person(name))
  }

  class Person(name: String) extends Actor {
    def receive: Receive = {
      case "hi" => println(s"Hi, my name is $name")
      case _ =>
    }
  }

  val person = actorSystem.actorOf(Person.props("Bob"))

  person ! "hi"*/


  // Domain of counter
  object Counter {

    case object Increment

    case object Decrement

    case object Print

  }

  class Counter extends Actor {

    import Counter._

    var count = 0

    override def receive: Receive = {
      case Increment => count += 1
      case Decrement => count -= 1
      case Print => println(s"[counter] My current count is $count")
    }
  }

  import Counter._

  val counter = actorSystem.actorOf(Props[Counter], "myCounter")
  (1 to 5).foreach(_ => counter ! Increment)
  (1 to 3).foreach(_ => counter ! Decrement)
  counter ! Print


  //Bank Account
  object BankAccount {

    case class Deposit(amount: Int)

    case class Withdraw(amount: Int)

    case object Statement

    case class TransactionSuccess(message: String)

    case class TransactionFailure(message: String)

  }

  class BankAccount extends Actor {

    import BankAccount._

    var funds = 0

    override def receive: Receive = {
      case Deposit(amount) =>
        if (amount < 0) sender() ! TransactionFailure("Invalid deposit amount")
        else {
          funds += amount
          sender() ! TransactionSuccess(s"Successfully deposited $amount")
        }
      case Withdraw(amount) =>
        if (amount < 0) sender() ! TransactionFailure("Invalid withdraw amount")
        else if (amount > funds) sender() ! TransactionFailure("Insufficient funds")
        else {
          funds -= amount
          sender() ! TransactionSuccess(s"Successfully withdraw $amount")
        }
      case Statement =>
        sender() ! s"Your balance is $funds"
    }
  }

  object Person {

    case class LiveTheLife(account: ActorRef)

  }

  class Person extends Actor {

    import BankAccount._
    import Person._

    override def receive: Receive = {
      case LiveTheLife(account) =>
        account ! Deposit(1000)
        account ! Statement
        account ! Withdraw(500)
        account ! Statement
        account ! Withdraw(300)
        account ! Statement
        account ! Withdraw(400)
        account ! Statement
      case message => println(message.toString)
    }
  }

  val account = actorSystem.actorOf(Props[BankAccount], "bankAccount")
  val person = actorSystem.actorOf(Props[Person], "person")

  person ! LiveTheLife(account)


}
