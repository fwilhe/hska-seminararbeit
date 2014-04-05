package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, promise }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }

object Main {

  def main(args: Array[String]) {
    val myPromise = promise[Integer]
    val myFuture = myPromise.future

    
    // keep the jvm running
    Thread.sleep(3000)
  }
}
