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
      
    val ratePound: Future[Double] = 
      Helper.getExchangeRateByFuture("Pound sterling")
      
    val rates = List(rateDollar, rateFranc, ratePound)

    val firstCompletedRateFuture = Future.firstCompletedOf(rates) 

    val bookAccommodation = firstCompletedRateFuture.map {
      rate => {
        println("Got exchange-rate. It is " + rate)
        if (Helper.isExchangeRateAcceptable(rate)) {
          println("Book accommodation")
          Helper.bookAccommodationOnline()
        } else {
          throw new Exception("not profitable")
        }
      }
    }    
    
    val bookFlight = bookAccommodation.map {
      successful => {
        println("Book flight")
        Helper.bookFlightOnline()
      }
    }
    
    bookFlight.onSuccess {
      case _ => println("Flight booked")
    }

    // do the rest of your work
    Helper.doSomeWork()

    // keep the jvm running
    Thread.sleep(3000)
  }
}
