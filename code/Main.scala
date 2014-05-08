package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random
import scala.collection.mutable
import scala.collection.mutable.Set

object Main {

  def readSignal(): Future[Integer] = future {
    Random.nextInt(100)
  }
  
  def checkSignals(signals: Set[Integer]): Boolean = {
    return Random.nextInt(6) <3
  }

  def main(args: Array[String]) {

    val listOfSignals = mutable.Set[Integer]()
    val collectedSignals = mutable.Set[Integer]()
    val f: Future[Integer] = readSignal()  

f.andThen(println("first")).andThen(println("second"))
/*
    f.andThen {
      //signal: Integer => listOfSignals ++= Set(signal)
      println("first")
    }.andThen {
      //checkSignals(listOfSignals)
      println("second")
    }
*/        
    f.onComplete {
      case Success(signal) => 
        println("Got signal")
      case Failure(ex) => 
        println(ex.getMessage)
    }
    
    Thread.sleep(500)
  }
}
