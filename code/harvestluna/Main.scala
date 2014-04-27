package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random
import scala.util.{ Failure, Success }

object Main {

  case class Cucumber(bendingInDegree: Int)

  def harvestCucumber(): Future[Cucumber] = future {
    Thread.sleep(Random.nextInt(100))
    new Cucumber(Random.nextInt(40))
  }
  
  def isStraightEnough(cucumber: Cucumber): Boolean = {
    cucumber.bendingInDegree < 20
  }
  
  def loadOnBox(cucumber: Cucumber) {
  
  }

  def main(args: Array[String]) {

    val cucumberFuture = harvestCucumber()
    
    val harvestFuture = cucumberFuture.map {
      cucumber => if (isStraightEnough(cucumber)) loadOnBox(cucumber)
      else throw new Exception("Can't sell this one")
    }
    
    harvestFuture.onComplete {
      case Success(_) => println("Harvested a Cucumber")
      case Failure(ex) => println(ex)
    }
    
    Thread.sleep(500)
  }
}
