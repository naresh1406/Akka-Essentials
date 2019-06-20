package part2actors

import akka.actor.{Actor, ActorSystem, Props}

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

  object Person {
    def props(name: String) = Props(new Person(name))
  }

  class Person(name: String) extends Actor {
    def receive: Receive = {
      case "hi" => println(s"Hi, my name is $name")
      case _ =>
    }
  }

  val person = actorSystem.actorOf(Person.props("Bob"))

  person ! "hi"

}
