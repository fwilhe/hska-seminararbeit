package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, promise }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

object Main {

  def main(args: Array[String]) {
    // create a promise which will take an integer
    val myPromise = promise[Integer]
    // get this promises future
    val myFuture = myPromise.future

    // complete the promise
    // usually the parameter is not hardcoded
    myPromise.success(23) 

    // will be called when the promise is completed
    myFuture.onSuccess {
      case result: Integer => {
        // do something with result
      }
    }
    
    // keep the jvm running
    Thread.sleep(3000)
  }
}
