package com.speelyaal.trom.runners

import com.speelyaal.trom.config.ConfigLoader
import com.speelyaal.trom.config.TromProperties
import com.speelyaal.trom.dsl.ActionType
import com.speelyaal.trom.dsl.Browsers
import com.speelyaal.trom.dsl.SelectorType
import com.speelyaal.trom.dsl.TestStep
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By
import org.openqa.selenium.By.linkText
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TestStepRunner {

    private val LOG: Logger = LogManager.getLogger(TestStepRunner::class.java)

    @Autowired
    lateinit var config: ConfigLoader

    @Autowired
    lateinit var tromProperties: TromProperties

    lateinit var webBrowserDriver: RemoteWebDriver


    fun openBrowser(browser: Browsers, url: String) {
        when (browser) {
            Browsers.firefox -> {
                System.setProperty("webdriver.gecko.driver", "/Users/ashok/SpeelYaal/Technology/Workspace/Tools/selenium/geckodriver")
                this.webBrowserDriver = FirefoxDriver()
                this.webBrowserDriver.get(url)
            }


        }

    }

    fun executeTestStep(testStep: TestStep) {

        var element = this.getElement(testStep)

        if (element != null) {
            when (testStep.actionType) {
                ActionType.enterText -> element.sendKeys(testStep.value.toString())
                ActionType.pressKey -> element.sendKeys(testStep.value as Keys)
                ActionType.click -> element.click()
                ActionType.waitForElement -> ExpectedConditions.visibilityOfElementLocated(this.getBy(testStep))
            }
        }else{
            LOG.error("Element is null for test step :  ${testStep.element}")
        }


    }

    private fun getElement(testStep: TestStep): WebElement? {

        var selectorString= testStep.element;
       return when (testStep.selectorType) {
            SelectorType.xpath ->  this.webBrowserDriver.findElementByXPath(selectorString)
            SelectorType.id ->  this.webBrowserDriver.findElementById(selectorString)
            SelectorType.css ->   this.webBrowserDriver.findElementByCssSelector(selectorString)
            SelectorType.linkText ->  this.webBrowserDriver.findElementByPartialLinkText(selectorString)
            else -> null
        }


    }

    private fun getBy(testStep: TestStep): By? {
        var selectorString = testStep.element;
        return when (testStep.selectorType) {
            SelectorType.xpath -> return By.xpath(selectorString)
            SelectorType.id -> return By.id(selectorString)
            SelectorType.css -> return By.cssSelector(selectorString)
            SelectorType.linkText -> return By.linkText(selectorString)
            else -> null

        }
    }
}