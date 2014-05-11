package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object Main {

  type ResultPage = String
  
  def query(provider: String, term: String): ResultPage = {
    Thread.sleep(Random.nextInt(1000))
    if (Random.nextInt(10) < 3) {
      throw new Exception("Server not reachable")
    } else {
      provider
    }
  }

  def main(args: Array[String]) {

    val queryGoogle: Future[ResultPage] = future {
      Thread.sleep(Random.nextInt(1000))
      if (Random.nextInt(10) < 3) {
        throw new Exception("Server not reachable")
      } else {
        "Result by Google"
      }
    }
    val queryBing: Future[ResultPage] = future {
      Thread.sleep(Random.nextInt(1000))
      if (Random.nextInt(10) < 3) {
        throw new Exception("Server not reachable")
      } else {
        "Result by Bing"
      }
    }
    val queryYahoo: Future[ResultPage] = future {
      Thread.sleep(Random.nextInt(1000))
      if (Random.nextInt(10) < 3) {
        throw new Exception("Server not reachable")
      } else {
        "Result by Yahoo"
      }
    }
    
    val query = queryGoogle.fallbackTo(queryBing).fallbackTo(queryYahoo)
    
    query.onComplete {
      case Success(result) => println(s"got my result: $result")
      case Failure(ex) => println("No result! " + ex)
    }


    Thread.sleep(2000)
  }
}
