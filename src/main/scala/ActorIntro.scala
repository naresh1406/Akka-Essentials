
import akka.actor.ActorSystem

object ActorIntro extends App {
  val actorSystem = ActorSystem("firstActor")

  println(actorSystem.name)

  //actors are uniquely indetifies

}
