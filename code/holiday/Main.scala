package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.util.Random

object Main {

  def main(args: Array[String]) {
    println("Trying to get the exchange-rate")
    
    val exchangeRate: Future[Double] = future {
      Thread.sleep(Random.nextInt(500))

      if (Helper.isExchangeServiceOnline()) {
        Helper.getExchangeRate()
      } else {
        throw new Exception("Exchange-Service not reachable.")
      }
    }

    exchangeRate onComplete {
      case Success(value) => {
        println("Got a exchange-rate of " + value)
        if (Helper.isExchangeRateAcceptable(value)) {
          println("Trying to book a flight")
          val bookFlight: Future[Boolean] = future {
            Thread.sleep(Random.nextInt(500))
            if (Helper.bookFlightOnline()) {
              true
            } else {
              throw new Exception("Booking-Service not reachable.")
            }
          }

          bookFlight onComplete {
            case Success(outcome) => {
              println("We can fly to hawaii now! \\o/")
            }
            
            case Failure(e) => println(e.getMessage())
          }
        } else {
          println("Exchange-rate is not acceptable. Don't book.")
        }
      }

      case Failure(e) => println(e.getMessage())
    }

    // do the rest of your work
    Helper.doSomeWork()

    // keep the jvm running
    Thread.sleep(3000)
  }
}
