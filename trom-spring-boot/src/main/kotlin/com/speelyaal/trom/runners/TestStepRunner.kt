package com.speelyaal.trom.runners

import com.speelyaal.trom.config.ConfigLoader
import com.speelyaal.trom.config.TromProperties
import com.speelyaal.trom.dsl.Browsers
import com.speelyaal.trom.dsl.TestStep
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TestStepRunner {

    @Autowired
    lateinit var config: ConfigLoader

    @Autowired
    lateinit var tromProperties: TromProperties

    fun openBrowser(browser: Browsers, url: String){
        when(browser){
            Browsers.firefox ->{

              this.config.firefoxDriver.get(url)



            }
        }

    }

    fun executeTestStep(testStep: TestStep){

    }
}