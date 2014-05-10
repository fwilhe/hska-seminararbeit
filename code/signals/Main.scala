package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object Main {

  def readSignal(): Integer = {
    Thread.sleep(500)
    Random.nextInt(100)
  }
  
  def logSignal() {
    println("logSignal() enter")
    Thread.sleep(500)
    println("logSignal() exit")
  }
  
  def checkSignals(): Boolean = {
    println("checkSignals() enter") 
    Thread.sleep(500)
    println("checkSignals() exit") 
    return Random.nextInt(6) <3
  }

  def main(args: Array[String]) {

    val signalFuture = future {
      readSignal()
    } andThen {
      case _ => {
        logSignal()
      }
    } andThen {
      case _ => {
        checkSignals()
      }
    }

    Console.println("Recieve Signals")

    Thread.sleep(2000)
  }
}
