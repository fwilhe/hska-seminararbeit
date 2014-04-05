package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.util.Random

object Main {

  def main(args: Array[String]) {

    val doctor1: Future[Integer] = future {
      Thread.sleep(Random.nextInt(500))
      42
    }
    val doctor2: Future[Integer] = future {
      Thread.sleep(Random.nextInt(500))
      23
    }
    val doctor3: Future[Integer] = future {
      Thread.sleep(Random.nextInt(500))
      1337
    }
    
    val doctors = List(doctor1, doctor2, doctor3)

    // Get any information which is available via several chanells, don't care who brings it
    val f = Future.firstCompletedOf(doctors)

    f onSuccess {
      case value => println(value)
    }

    // keep the jvm running
    Thread.sleep(1000)
  }
}
