package com.speelyaal.trom.runners

import com.speelyaal.trom.config.ConfigLoader
import com.speelyaal.trom.dsl.TestCase
import com.speelyaal.trom.dsl.TestSuite
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class TestSuitesRunner {

    private val LOG: Logger = LogManager.getLogger(TestSuitesRunner::class.java)

    @Autowired
    lateinit var configLoader: ConfigLoader

    @Autowired
    lateinit var testStepRunner: TestStepRunner



    @PostConstruct
    fun executeTestSuites() {
        println("execution of test suites called")

        this.configLoader.testSuites.forEach { testSuiteEntry ->
            try {
                var testSuite= testSuiteEntry.value

                LOG.info("[TEST-SUITE] Executing Test Suite : ${testSuite.name}")

                testSuite.browsers.forEach { browser->

                    testStepRunner.openBrowser(browser, testSuite.url)

                    testSuite.testCases.forEach { case ->
                        var testCase: TestCase? = this.configLoader.testCases[case]
                        LOG.info("[TEST-CASE] Executing Test Case : ${testCase!!.name}")
                        testCase.testSteps.forEach { testStep ->
                            LOG.info("[TEST-STEP] Executing Test Step : ${testStep.name}")
                            testStepRunner.executeTestStep(testStep);
                        }
                    }

                }

                testSuite.browsers.forEach {
                    println("Browsers $it")
                }

            } catch (exception: IllegalArgumentException) {
                println("error after executing script")
            }
            // println(fromScript)


        }
    }

}