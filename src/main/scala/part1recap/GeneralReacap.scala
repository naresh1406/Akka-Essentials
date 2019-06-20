package part1recap

object GeneralReacap extends App {

  val aCondition: Boolean = false

  println(aCondition)

  var aVariable = 45

  println(aVariable)

  aVariable += 1

  println(aVariable)

  val aCodeBlock = {
    if (aCondition) 74
    56
  }

  println(aCodeBlock)

  val aUnit = println("Hello World")

  println(aUnit) //side-effects

  def aFunction(x: Int): Int = x + 1

  val result = List(1, 2, 3, 4).flatMap(num => List('a', 'b', 'c', 'd').map(char => num + "-" + char))
  println(result)

  // for comprehensions
  val pairs = for {
    num <- List(1, 2, 3, 4)
    char <- List('a', 'b', 'c', 'd')
  } yield num + "-" + char

  println(pairs)

  // Seq, List, Array, Vector, Map, Tuples
  //Options

  val aOption = Some(1)

  // Pattern matching
  val unknown = 2
  val order = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "whatever"
  }

  println(order)


}
