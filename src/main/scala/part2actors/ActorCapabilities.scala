package part2actors

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by ngupta on 2019-06-06.
  */
object ActorCapabilities extends App {

  class SimpleActor extends Actor {
    override def receive: Receive = {
      case message: String => println(s"[simple actor] I have received $message")
    }
  }

  val system = ActorSystem("actorCapabilitiesDemo")
  val simpleActor = system.actorOf(Props[SimpleActor], "simpleActor")

  simpleActor ! "hello, actor"


}
