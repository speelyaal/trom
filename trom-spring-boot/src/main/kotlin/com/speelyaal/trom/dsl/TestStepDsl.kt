package com.speelyaal.trom.dsl

import org.openqa.selenium.By

@DslMarker
annotation class TromTestStepDslMarker

enum class ActionType {
    click,
    enterText,
    pressKey,
    waitForElement
}

enum class SelectorType{
    xpath,
    id,
    nameAttribute,
    css,
    linkText
}



@TromTestStepDslMarker
class TestStep(var name: String="") {

    var element: String = ""
    var value: Any = ""
    var actionType = ActionType.click
    var selectorType = SelectorType.xpath

    fun click(lambda: TestStep.() -> Unit) {
        val testStep = TestStep()
        testStep.lambda();
        // println("I am inside lambda " + testString)
        this.element = testStep.element
        this.value = testStep.value
        this.actionType = ActionType.click

    }

    fun enterText(value: String, lambda: TestStep.() -> Unit): TestStep {
        val testStep = TestStep()
        testStep.lambda();
        // println("I am inside lambda " + testString)
        this.element = testStep.element
        this.value = if (value.isNullOrBlank()) testStep.value else value
        this.actionType = ActionType.enterText
        return this
    }


    fun xpath(xpath: String=""): String{//HTMLElement
        this.selectorType = SelectorType.xpath
        return xpath
    }

    fun name(name: String=""): String{//HTMLElement
        this.selectorType = SelectorType.nameAttribute
        return name
    }

    fun id(id: String=""): String{//HTMLElement
        this.selectorType = SelectorType.id
        return id
    }

    fun css(css: String=""): String{//HTMLElement
        this.selectorType = SelectorType.css
        return css
    }

    fun linkText(linkText: String=""): String{//HTMLElement
        this.selectorType = SelectorType.linkText
        return linkText
    }






}


fun testStep(testStepName: String, lambda: TestStep.() -> Unit) : TestStep =  TestStep(testStepName).apply(lambda);