package com.speelyaal.trom.runners

import com.aventstack.extentreports.MediaEntityBuilder
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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.concurrent.TimeUnit
import  org.openqa.selenium.NoSuchElementException
import java.lang.Exception

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

        try {

            when (testStep.actionType) {
                ActionType.enterText -> this.getElement(testStep)!!.sendKeys(testStep.value.toString())
                ActionType.pressKey -> this.getElement(testStep)!!.sendKeys(testStep.value as Keys)
                ActionType.click -> this.getElement(testStep)!!.click()
                ActionType.waitForElement -> {
                    val wait = FluentWait(this.webBrowserDriver)
                            .withTimeout(Duration.ofSeconds(30))
                            .pollingEvery(Duration.ofSeconds(3))
                            .ignoring(NoSuchElementException::class.java)
                    wait.until(ExpectedConditions.visibilityOfElementLocated(this.getBy(testStep)))

                    LOG.debug("I am waiting until this appear ${testStep.element}")
                }
                ActionType.waitForSeconds -> {
                    webBrowserDriver.manage().timeouts().implicitlyWait(testStep.value.toString().toLong(), TimeUnit.SECONDS)

                }
            }
            this.config.currentTest.pass("Test step ${testStep.name} passed ")
        }catch (exception: Exception){
            //FIXME: Move all these pass, fail logging and screenshot to a Report wrapper
            val temp= this.config.screenShotUtil.getScreenShot(this.webBrowserDriver);
            this.config.currentTest.fail("Test step ${testStep.name} failed " + exception.message, MediaEntityBuilder.createScreenCaptureFromPath(temp).build())
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
            SelectorType.linkText -> return By.partialLinkText(selectorString)
            else -> null

        }
    }
}