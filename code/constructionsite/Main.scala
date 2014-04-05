package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, promise }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }

object Main {

  def main(args: Array[String]) {
    val myPromise = promise[Integer]
    val myFuture = myPromise.future

    val apprentice = future {
      val toolbox: Integer = Helper.getToolbox()
      myPromise.success(toolbox) // complete the promise
      Helper.cleanConstructionSite()
    }

    val craftsman = future {
      Helper.takeMeasurement()
      myFuture.onSuccess {
        case toolbox: Integer => Helper.pickTheRightTool(toolbox)
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
