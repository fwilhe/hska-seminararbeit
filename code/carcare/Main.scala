package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object Main {

  type Tire = String

  def getNewTireAsFuture(): Future[Tire] = future {
    Thread.sleep(Random.nextInt(100))
    "Tire By Brand XY"
  }
  
  def replaceTire(tire: Tire): Future[Boolean] = future {
    Thread.sleep(Random.nextInt(100))
    true  
  }
  
  def changeTires() {
    // use "for"-statement to combine futures. "replaceTire()" depends
    // on "getNewTireAsFuture()"
    for {
      tire <- getNewTireAsFuture()
      success <- replaceTire(tire)
    } yield println("got response " + success)
  }
  
  def cleanCar() {
    println("cleaning ..."); Thread.sleep(100)
    println("cleaning ..."); Thread.sleep(100)
    println("cleaning ..."); Thread.sleep(100)
  }

  def main(args: Array[String]) {
    changeTires()
    cleanCar()
    
    Thread.sleep(500)
  }
}
