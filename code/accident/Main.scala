package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.util.Random

object Main {

  def main(args: Array[String]) {

    val doctor1: Future[String] = future {
      Thread.sleep(Random.nextInt(500))
      "doctor from the nearby hospital"
    }
    
    val doctor2: Future[String] = future {
      Thread.sleep(Random.nextInt(500))
      "doctor from the quick response team"
    }
    
    val doctor3: Future[String] = future {
      Thread.sleep(Random.nextInt(500))
      "doctor who just happens to enjoy the same trip"
    }
    
    val doctors = List(doctor1, doctor2, doctor3)

    val f = Future.firstCompletedOf(doctors)

    f onSuccess {
      case value => println("The first doctor is here.\nIt's the " + value + ".")
    }

    // keep the jvm running
    Thread.sleep(1000)
  }
}
