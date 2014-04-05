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
  
  def cleanConstructionSite() {
    println("** cleanConstructionSite **")
    println("*clean* A ..."); Thread.sleep(100)
    println("*clean* B ..."); Thread.sleep(100)
    println("*clean* C ..."); Thread.sleep(100)
    println("*clean* D ..."); Thread.sleep(100)
  }

  def takeMeasurement() {
    println("** takeMeasurement **")
    println("*measure* α ..."); Thread.sleep(100)
    println("*measure* β ..."); Thread.sleep(100)
    println("*measure* γ ..."); Thread.sleep(100)
    println("*measure* δ ..."); Thread.sleep(100)
  }
  
  def getToolbox(): Integer = {
    println("** getToolbox **")
    Thread.sleep(200)
    Helper.calculateResult(Random.nextInt(10))
  }

  def pickTheRightTool(result: Integer) {
    if (Helper.isValid(result)) {
      println("Got the right tool, get working")
    } else {
      println("The toolbox does not have the right tool.")
    }
  }

}
