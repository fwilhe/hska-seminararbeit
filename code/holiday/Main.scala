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

    val f = Future.firstCompletedOf(rates) 

    f.andThen {
      case Success(rate) => {
        //TODO woher weiß ich welcher Future das jetzt ist?
        println("The rate is: " + rate)
        val bookFlight: Future[Boolean] = Helper.bookFlightOnline()
        
        bookFlight.onSuccess {
          case successful: Boolean => println("Flight booked")
        }
      }
    }      

    // do the rest of your work
    Helper.doSomeWork()

    // keep the jvm running
    Thread.sleep(3000)
  }
}
