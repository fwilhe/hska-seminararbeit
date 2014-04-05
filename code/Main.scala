package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, promise }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }

object Main {

  def main(args: Array[String]) {
    val myPromise = promise[Integer]
    val myFuture = myPromise.future

    val producer = future {
      val r: Integer = Helper.produceSomething()
      myPromise.success(r) // complete the promise
      Helper.continueDoingSomethingUnrelated()
    }

    val consumer = future {
      Helper.startDoingSomething()
      myFuture.onSuccess {
        case r: Integer => Helper.doSomethingWithResult(r)
      }
    }
    
    // Doing this would cause an execption because the promise has already
    // been completed.
    // Thread.sleep(1000)
    // myPromise.success(23)
    
    // keep the jvm running
    Thread.sleep(3000)
  }
}
