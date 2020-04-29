package com.speelyaal.trom.dsl

import org.openqa.selenium.By

@DslMarker
annotation class TromTestStepDslMarker

enum class ActionType {
    click,
    enterText,
    pressKey,
    waitForElement,
    waitForSeconds
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