package part2actors

import akka.actor.{Actor, ActorSystem, Props}

object ActorIntro extends App {

  val actorSystem = ActorSystem("firstActorSystem")
  println(actorSystem.name)


  class WordCountActor extends Actor {
    var totalWords = 0;

    def receive: PartialFunction[Any, Unit] = {
      case message: String =>
        println(s"[word counter] I have received: $message")
        totalWords += message.split(" ").length

      case msg => println(s"[word counter] I cannot understand ${msg.toString}")
    }
  }

  //initiation of actor
  val wordCounter = actorSystem.actorOf(Props[WordCountActor], "wordCounter")
  val anotherWordCounter = actorSystem.actorOf(Props[WordCountActor], "anotherWordCounter")

  //communicate
  wordCounter ! "I am learning Akka and its pretty cool!" // ! --> tell
  anotherWordCounter ! "I different message"



}
