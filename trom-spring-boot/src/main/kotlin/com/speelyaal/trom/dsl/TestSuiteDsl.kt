package com.speelyaal.trom.dsl

@DslMarker
annotation class TromTestSuiteDslMarker

@TromTestSuiteDslMarker
class TestSuiteDsl {

    fun testCases(lambda: String.() -> Unit): String {
        val testString=  String()
        testString.lambda();
        println("I am inside lambda " + testString)
        return ""
    }

}

fun testSuite(testSuiteName: String, lambda: TestSuiteDsl.() -> Unit) =  TestSuiteDsl().apply(lambda);