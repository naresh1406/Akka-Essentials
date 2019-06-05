package part1recap

object GeneralReacap extends App {

  val aCondition: Boolean = false

  println(aCondition)

  var aVariable = 45

  println(aVariable)

  aVariable += 1

  println(aVariable)

  val aUnit = println("Hello World")

  println(aUnit) //side-effects

  val result = List(1, 2, 3, 4).flatMap(num => List('a', 'b', 'c', 'd').map(char => num + "-" + char))
  println(result)

  // for comprehensions
  val pairs = for {
    num <- List(1, 2, 3, 4)
    char <- List('a', 'b', 'c', 'd')
  } yield num + "-" + char

  println(pairs)

}
