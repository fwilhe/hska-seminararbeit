package de.hska.wifl1011.seminararbeit

import scala.util.Random
import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global

object Helper {
  def doSomeWork() {
    println("A ..."); Thread.sleep(100)
    println("B ..."); Thread.sleep(100)
    println("C ..."); Thread.sleep(100)
    println("D ..."); Thread.sleep(100)
    println("E ..."); Thread.sleep(100)
    println("F ..."); Thread.sleep(100)
  }
  
  def isExchangeServiceOnline() = Random.nextInt(6) < 4
  
  def getExchangeRate() = Math.abs(Random.nextGaussian())
  
  def isExchangeRateAcceptable(rate: Double) = rate > 0.3
  
  def bookFlightOnline(): Future[Boolean] = future {
    Thread.sleep(Random.nextInt(100))
    Random.nextInt(6) < 5
  } 
  
  def bookAccommodationOnline(): Future[Boolean] = future {
    Thread.sleep(Random.nextInt(100))
    Random.nextInt(6) < 5
  }
  
  def getExchangeRateByFuture(currency: String): Future[Double] = future {
    println("Getting exchange-rate for " + currency)
    Thread.sleep(Random.nextInt(500))

    if (isExchangeServiceOnline()) {
      getExchangeRate()
    } else {
      throw new Exception("Exchange-Service not reachable.")
    }
  }
}
