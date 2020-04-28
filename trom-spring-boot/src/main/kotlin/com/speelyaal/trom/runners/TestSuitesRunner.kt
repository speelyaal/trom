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



    fun executeTestSuites() {
        println("execution of test suites called")

        this.configLoader.testSuites.forEach { testSuiteScript ->


            val scriptContent = testSuiteScript.value.readText()

            try {

                LOG.info("Loading Test Suite Script : ${testSuiteScript.key}")
                val testSuite: TestSuite = KotlinScriptRunner.executeScript(scriptContent)
                LOG.info("Executing Test Suite : ${testSuite.name}")

                testSuite.browsers.forEach { browser->

                    testStepRunner.openBrowser(browser, testSuite.url)

                    testSuite.testCases.forEach { case ->


                        LOG.info("Loading Test Case Script : ${case}")
                        var testCaseToRun = this.configLoader.testCases[case]!!.readText()


                        val testCase: TestCase = KotlinScriptRunner.executeScript(testCaseToRun)
                        LOG.info("Executing Test Case : ${testCase.name}")



                        testCase.testSteps.forEach { testStep ->
                            LOG.info("Executing Test Step : ${testStep.name}")
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