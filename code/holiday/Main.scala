package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.util.Random

object Main {

  def main(args: Array[String]) { 
    val rateDollar: Future[Double] = 
      Helper.getExchangeRateByFuture("US-Dollar")
      
    val rateFranc: Future[Double] = 
      Helper.getExchangeRateByFuture("Swiss franc")
      
    val selectedRate = rateDollar.fallbackTo(rateFranc)

    val bookAccommodation = selectedRate.map {
      rate => {
        println("Got exchange-rate. It is " + rate)
        if (Helper.isExchangeRateAcceptable(rate)) {
          println("Book accommodation")
          Helper.bookAccommodationOnline()
        } else {
          throw new Exception("Rate not acceptable")
        }
      }
    }    
    
    val bookFlight = bookAccommodation.map {
      successful => {
        println("Book flight")
        Helper.bookFlightOnline()
      }
    }
    
    bookFlight.onComplete {
      case Success(_) => println("Flight booked")
      case Failure(ex) => println(ex.getMessage)
    }

    // do some unrelated work
    Helper.packSuitcase()

    // keep the jvm running
    Thread.sleep(3000)
  }
}
