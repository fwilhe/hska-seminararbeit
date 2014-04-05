package de.hska.wifl1011.seminararbeit

import scala.util.Random

object Helper {
  def calculateResult(n: Integer): Integer = {
    if (n > 0) {
      calculateResult(n-1) + n
    } else {
      n
    }
  }
  
  def isValid(result: Integer): Boolean = {
    (result % 3) != 0
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
  
  def produceSomething(): Integer = {
    println("** produceSomething **")
    Thread.sleep(200)
    Helper.calculateResult(Random.nextInt(10))
  }

  def doSomethingWithResult(result: Integer) {
    println("** doSomethingWithResult **")
    println("The result is: " + result)
    println("Result is valid?: " + Helper.isValid(result))
  }

}
