package de.hska.wifl1011.seminararbeit

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random

object Main {

  def produceSomething() = {
    println("** produceSomething **")
    Thread.sleep(200)
    42
  }
  
  def continueDoingSomethingUnrelated() {
    println("** continueDoingSomethingUnrelated **")
    println("A ..."); Thread.sleep(100)
    println("B ..."); Thread.sleep(100)
    println("C ..."); Thread.sleep(100)
    println("D ..."); Thread.sleep(100)
  }
  
  def startDoingSomething() {
    println("** startDoingSomething **")
    println("α ..."); Thread.sleep(100)
    println("β ..."); Thread.sleep(100)
    println("γ ..."); Thread.sleep(100)
    println("δ ..."); Thread.sleep(100)
  }
  
  def doSomethingWithResult() {
    println("** doSomethingWithResult **")
  }
  

  def main(args: Array[String]) {
    val p = promise[Integer]
    val f = p.future
    
    val producer = future {
      val r = produceSomething()
	p success r
	continueDoingSomethingUnrelated()
      }
      
    val consumer = future {
      startDoingSomething()
      f onSuccess {
        case r => doSomethingWithResult()
      }
    }
    
    // keep the jvm running
    Thread.sleep(3000) 
  }
}
