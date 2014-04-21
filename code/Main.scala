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
    for {
      tire <- getNewTireAsFuture()
      success <- replaceTire(tire)
    } yield println("got response " + success)
  }
  
  def doSomeWork() {
    println("A ..."); Thread.sleep(100)
    println("B ..."); Thread.sleep(100)
    println("C ..."); Thread.sleep(100)
    println("D ..."); Thread.sleep(100)
    println("E ..."); Thread.sleep(100)
    println("F ..."); Thread.sleep(100)
  }

  def main(args: Array[String]) {
    changeTires()
    doSomeWork()
    
    Thread.sleep(500)
  }
}
