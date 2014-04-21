package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object Main {

  case class Cucumber(bendingInDegree: Int)

  def harvestCucumber(): Future[Cucumber] = future {
    Thread.sleep(Random.nextInt(100))
    new Cucumber(Random.nextInt(40))
  }

  def main(args: Array[String]) {

    val cucumbers = List(harvestCucumber(), harvestCucumber(), harvestCucumber())
    
    println(cucumbers)
    // cucumbers.filter(() => )
    
    Thread.sleep(500)
  }
}
