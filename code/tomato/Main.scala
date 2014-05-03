package de.hska.wifl1011.seminararbeit

import scala.concurrent.{ future, Future }
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object Main {

  case class Tomato(color: String)
  
  def harvestTomato(): Future[Tomato] = future {
    if (Random.nextInt(10) < 3)
      new Tomato("FE56A0")
    else
      new Tomato("FF1011")
  }

  def main(args: Array[String]) {

    val t: Future[Tomato] = harvestTomato()  
    val tt: Future[Tomato] = 
      t.filter(_.color.startsWith("FF"))
        
    tt.onComplete {
      case Success(theTomato) => 
        println("Harvested " + theTomato)
      case Failure(ex) => 
        println(ex.getMessage)
    }
    
    Thread.sleep(500)
  }
}
