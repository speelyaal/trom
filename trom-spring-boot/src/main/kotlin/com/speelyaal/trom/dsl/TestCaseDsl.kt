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
        var tmpStep = TestStep("Press Enter Key")
        tmpStep.actionType = ActionType.pressKey
        tmpStep.element = this.testSteps.last().element
        tmpStep.value = Keys.RETURN

        this.testSteps.add(tmpStep)
        return  this
    }

    fun waitForElement(reason: String="", lambda: TestStep.() -> Unit): TestCase{
        val testStep=  TestStep()
        testStep.lambda();
        testStep.actionType = ActionType.waitForElement
        testStep.name = if(reason.isNullOrBlank()) "Waiting for element ${testStep.element}" else reason
        this.testSteps.add(testStep)
        return this
    }

    fun waitForSeconds(seconds: Long, reason: String=""): TestCase{
        val testStep=  TestStep()
        testStep.actionType = ActionType.waitForSeconds
        testStep.name = if(reason.isNullOrBlank()) "Waiting for ${seconds} seconds" else reason
        testStep.value = seconds
        this.testSteps.add(testStep)
        return this
    }


    fun click(name: String, lambda: TestStep.() -> Unit): TestCase {
        val testStep = TestStep(name)
        testStep.lambda();

        testStep.actionType = ActionType.click
        this.testSteps.add(testStep)

        return this
    }

  /*  @JvmName("clickElement")
    fun click(name: String, lambda: String.() -> Unit): TestCase {
        val testStep = TestStep(name)
        val elementSelector = String()
        elementSelector.lambda();
        testStep.element= elementSelector
        testStep.actionType = ActionType.click
        this.testSteps.add(testStep)

        return this
    }*/


    fun enterText(stepName: String = "", value: String,  lambda: TestStep.() -> Unit): TestCase {
        val testStep = TestStep(stepName)
        testStep.lambda();

        testStep.value = if (value.isNullOrBlank()) testStep.value else value
        testStep.actionType = ActionType.enterText
        this.testSteps.add(testStep)

        return this
    }

  /*@JvmName("enterTextValue")
    fun enterText(stepName: String = "", value: String,  lambda: String.() -> Unit): TestCase {
        val testStep = TestStep(stepName)
        val elementSelector = String()
        elementSelector.lambda();
        testStep.element = elementSelector
        testStep.value = if (value.isNullOrBlank()) testStep.value else value
        testStep.actionType = ActionType.enterText
        this.testSteps.add(testStep)

        return this
    }*/



}


fun testCase(testCaseName: String="", lambda: TestCase.() -> Unit) : TestCase =  TestCase(testCaseName).apply(lambda);