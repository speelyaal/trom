package com.speelyaal.trom.example

import com.speelyaal.trom.runners.TestSuitesRunner
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @Autowired
    lateinit var testSuitesRunner: TestSuitesRunner

    @GetMapping("/execute")
    fun execute(){

       // System.setProperty("webdriver.gecko.driver", "/Users/ashok/SpeelYaal/Technology/Workspace/Tools/selenium/geckodriver")
        //var firefoxDriver = FirefoxDriver()
        this.testSuitesRunner.executeTestSuites()
    }
}