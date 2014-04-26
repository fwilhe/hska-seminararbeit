// This is ugly on purpose. Just to show how it ends up
// if you use nesting.

package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.util.Random

object Main {

  def doBookAccommodation(rate: Double): Future[Boolean] = {
    println("Got exchange-rate. It is " + rate)
    if (Helper.isExchangeRateAcceptable(rate)) {
      println("Book accommodation")
      Helper.bookAccommodationOnline()
    } else {
      throw new Exception("Rate not acceptable")
    }
  }
  
  def doBookFlight(bookAccommodation: Future[Boolean]) {
    bookAccommodation.onComplete {
      case Success(_) =>  {
        println("Accommodation booked")
        val bookFlight = future{
          println("Book flight")
          Helper.bookFlightOnline()        
        }
        bookFlight.onComplete {
          case Success(_) => println("Flight booked")
          case Failure(ex) => println(ex.getMessage)
        }
      }
      case Failure(ex) => println(ex.getMessage)
    }
  }

  def main(args: Array[String]) { 
    val rateDollar: Future[Double] = 
      Helper.getExchangeRateByFuture("US-Dollar")
      
    val rateFranc: Future[Double] = 
      Helper.getExchangeRateByFuture("Swiss franc")
      
    rateDollar.onComplete {
      // US-$
      case Success(rate) => {
        val bookAccommodation = doBookAccommodation(rate)
        doBookFlight(bookAccommodation)
      }
      
      // Swiss franc
      case Failure(ex) => {
        rateFranc.onComplete {
          case Success(rate) => {
            val bookAccommodation = doBookAccommodation(rate)
            doBookFlight(bookAccommodation)
          }
          case Failure(ex) => println(ex.getMessage)
        }
      }
    }
   
    // do some unrelated work
    Helper.packSuitcase()

    // keep the jvm running
    Thread.sleep(3000)
  }
}
