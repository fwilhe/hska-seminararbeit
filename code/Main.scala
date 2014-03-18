package de.hska.wifl1011.seminararbeit

import scala.concurrent.future
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random

object Main {

  def main(args: Array[String]) {

    val f: Future[Int] = future {
      Thread.sleep(Random.nextInt(500))

      // sometimes things work, sometimes they don't...
      if (Random.nextInt(6) < 3) {
        42
      } else {
        throw new Exception
      }
    }

    f onComplete {
      case Success(value) => println("We have the answer! It's " + value)
      case Failure(e) => e.printStackTrace
    }

    // do the rest of your work
    println("A ..."); Thread.sleep(100)
    println("B ..."); Thread.sleep(100)
    println("C ..."); Thread.sleep(100)
    println("D ..."); Thread.sleep(100)
    println("E ..."); Thread.sleep(100)
    println("F ..."); Thread.sleep(100)

    // keep the jvm running
    Thread.sleep(2000)
  }
}
