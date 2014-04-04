package de.hska.wifl1011.seminararbeit

import scala.concurrent.future
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.util.Random

object Main {

  def doSomeWork() {
    println("A ..."); Thread.sleep(100)
    println("B ..."); Thread.sleep(100)
    println("C ..."); Thread.sleep(100)
    println("D ..."); Thread.sleep(100)
    println("E ..."); Thread.sleep(100)
    println("F ..."); Thread.sleep(100)
  }

  def main(args: Array[String]) {
    println("Trying to get the exchange-rate")
    val exchangeRate: Future[Int] = future {
      Thread.sleep(Random.nextInt(500))

      // sometimes things work, sometimes they don't...
      if (Random.nextInt(6) < 4) {
        Random.nextInt(100)
      } else {
        throw new Exception
      }
    }

    exchangeRate onComplete {
      case Success(value) => {
        println("Trying to book a flight")
        val bookFlight: Future[Int] = future {
          Thread.sleep(Random.nextInt(500))
          1
        }

        bookFlight onSuccess {
          case msg => println("We can fly to hawaii now! \\o/")
        }
      }

      case Failure(e) => println("Ooops, an error occured.")
    }

    // do the rest of your work
    doSomeWork()

    // keep the jvm running
    Thread.sleep(3000)
  }
}
