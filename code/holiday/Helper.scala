package de.hska.wifl1011.seminararbeit

import scala.util.Random

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
  
  def bookFlightOnline() = Random.nextInt(6) < 5
}
