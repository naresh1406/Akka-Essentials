package part2actors

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by ngupta on 2019-06-06.
  */
object ActorCapabilities extends App {

  class SimpleActor extends Actor {
    override def receive: Receive = {
      case message: String => println(s"[${context.self.path}] I have received $message")
      case SpecialMessage(content) => println()
      case SendMessagesToYourself(content) =>
        self ! content
      //case sayHiTo(ref) => ref ! "Hi!"
    }
  }

  val system = ActorSystem("actorCapabilitiesDemo")
  val simpleActor = system.actorOf(Props[SimpleActor], "simpleActor")

  simpleActor ! "hello, actor" // tell

  //actors have information about their context and about themselves
  //context.self === `this` in OOP

  case class SpecialMessage(content: String)
  simpleActor ! SpecialMessage("Hello special world")

  case class SendMessagesToYourself(content: String)

  simpleActor ! SendMessagesToYourself("send message to yourself!")

  //actor can reply to messages
  val alice = system.actorOf(Props[SimpleActor], "alice")
  val bob = system.actorOf(Props[SimpleActor], "bob")

  // forwarding
  // D -> A -> B


}
