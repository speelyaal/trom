package com.speelyaal.trom.dsl

import org.openqa.selenium.Keys

@DslMarker
annotation class TromTestCaseDslMarker

@TromTestCaseDslMarker
class TestCase(var name: String="") {


    var testSteps: ArrayList<TestStep> = ArrayList()

    fun testStep(testStepName: String="", lambda: TestStep.() -> Unit):TestCase{
        val testStep=  TestStep(testStepName)
        testStep.lambda();
       // println("I am inside lambda " + testString)
       this.testSteps.add(testStep)
        return this
    }

    fun step(testStepName: String="", lambda: TestStep.() -> Unit): TestCase{
        this.testStep(testStepName, lambda)
        return this
    }

    fun pressEnter(): TestCase{
        var tmpStep = TestStep()
        tmpStep.actionType = ActionType.pressKey
        tmpStep.element = this.testSteps.last().element
        tmpStep.value = Keys.RETURN

        this.testSteps.add(tmpStep)
        return  this
    }

    fun waitForElement(reason: String="", lambda: TestStep.() -> Unit): TestCase{
        val testStep=  TestStep(reason)
        testStep.lambda();
        testStep.actionType = ActionType.waitForElement
        this.testSteps.add(testStep)
        return this
    }


}


fun testCase(testCaseName: String="", lambda: TestCase.() -> Unit) : TestCase =  TestCase(testCaseName).apply(lambda);