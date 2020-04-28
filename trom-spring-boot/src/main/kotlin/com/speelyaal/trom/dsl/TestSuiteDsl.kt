package com.speelyaal.trom.dsl

@DslMarker
annotation class TromTestSuiteDslMarker

enum class Browsers {
    chrome,
    firefox,
    safari,
    opera
}

@TromTestSuiteDslMarker
class TestSuite(var name: String="") {

    var testCases: ArrayList<String> = ArrayList();
    var browsers: ArrayList<Browsers> = ArrayList();
    var url: String =""

    val chrome = Browsers.chrome
    val firefox = Browsers.firefox
    val safari = Browsers.safari

    fun testCases(lambda: String.() -> Unit): String {
        val testString=  String()
        testString.lambda();
        println("I am inside lambda " + testString)
        return ""
    }

    fun browsers(vararg browsers: Browsers)  {
        val testString=  String()
        this.browsers.addAll(browsers)
    }

    fun url(url: String){
        this.url = url
    }


    operator fun String.unaryPlus(){
       testCases.add(this)

    }

}

fun testSuite(testSuiteName: String, lambda: TestSuite.() -> Unit) : TestSuite =  TestSuite(testSuiteName).apply(lambda);