package com.speelyaal.trom.dsl

@DslMarker
annotation class TromTestCaseDslMarker

@TromTestCaseDslMarker
class TestCase(var name: String="") {


    var testSteps: ArrayList<TestStep> = ArrayList()

    fun testStep(testStepName: String="", lambda: TestStep.() -> Unit){
        val testStep=  TestStep(testStepName)
        testStep.lambda();
       // println("I am inside lambda " + testString)
       this.testSteps.add(testStep)
    }


}


fun testCase(testCaseName: String="", lambda: TestCase.() -> Unit) : TestCase =  TestCase(testCaseName).apply(lambda);