package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.util.Random

object Main {

  def main(args: Array[String]) {

    val f: Future[Integer] = future {
      Thread.sleep(Random.nextInt(500))
      42
    }
    
    f onSuccess {
      case value => println(value)
    }

    // keep the jvm running
    Thread.sleep(1000)
  }
}
