package com.speelyaal.trom.dsl

@DslMarker
annotation class TromTestStepDslMarker

enum class ActionType {
    click,
    enterText
}

class Action{
    var type: ActionType= ActionType.click
    var element = ""
}

@TromTestStepDslMarker
class TestStep(var name: String="") {

    lateinit var action: Action

    fun click(lambda: Action.() -> Unit) {
        val givenAction = Action()
        givenAction.lambda();
        // println("I am inside lambda " + testString)
        givenAction.type = ActionType.click
        this.action = givenAction
    }

    fun xpath(xpath: String=""): String{//HTMLElement

        println(xpath);
        return String()
    }




}


fun testStep(testStepName: String, lambda: TestStep.() -> Unit) : TestStep =  TestStep(testStepName).apply(lambda);