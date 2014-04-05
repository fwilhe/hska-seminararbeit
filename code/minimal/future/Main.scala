package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

object Main {
  def main(args: Array[String]) {

    // create a future which will hold an integer value
    val f: Future[Integer] = future {
      // some computation here
      // this will run concurrently to the main-thread
      42  // return the result of the future
    }
    
    f onSuccess {
      case value => {
        // do something with result here
        // will be called when result is available
      }
    }

    // keep the jvm running
    Thread.sleep(1000)
  }
}
