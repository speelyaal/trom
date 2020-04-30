package com.speelyaal.trom.runners

import com.aventstack.extentreports.reporter.BasicFileReporter
import com.aventstack.extentreports.reporter.ExtentHtmlReporter
import com.speelyaal.trom.config.ConfigLoader
import com.speelyaal.trom.dsl.TestCase
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.FileCopyUtils
import java.util.*
import javax.annotation.PostConstruct

@Component
class TestSuitesRunner {

    private val LOG: Logger = LogManager.getLogger(TestSuitesRunner::class.java)

    @Autowired
    lateinit var config: ConfigLoader

    @Autowired
    lateinit var testStepRunner: TestStepRunner





    @PostConstruct
    fun executeTestSuites() {


        val nowTime= Date().toString()


        //FIXME: HTML reporter is deprecated. Move to another reporter
        val htmlReporter = ExtentHtmlReporter(this.config.tromProperties.rootPath + "/reports/report-${nowTime}.html")
        this.config.testReport.attachReporter(htmlReporter)


        this.config.testSuites.forEach { testSuiteEntry ->
            //TODO: Make report directory for each test suite

            this.config.currentTest = this.config.testReport.createTest(testSuiteEntry.key)


            try {
                var testSuite= testSuiteEntry.value

                LOG.info("[TEST-SUITE] Executing Test Suite : ${testSuite.name}")

                testSuite.browsers.forEach { browser->

                    testStepRunner.openBrowser(browser, testSuite.url)

                    testSuite.testCases.forEach { case ->
                        var testCase: TestCase? = this.config.testCases[case]
                        LOG.info("[TEST-CASE] Executing Test Case : ${testCase!!.name}")
                        testCase.testSteps.forEach { testStep ->
                            LOG.info("[TEST-STEP] Executing Test Step : ${testStep.name}")
                            testStepRunner.executeTestStep(testStep);
                        }

                        this.config.currentTest.pass("Test Case ${case} passed for ${browser}")
                    }

                    this.config.currentTest.pass("Test suite ${testSuiteEntry.key} passed for ${browser}")

                }



            } catch (exception: IllegalArgumentException) {
                println("error after executing script")
                this.config.currentTest.fail("Test suite ${testSuiteEntry.key} failed " + exception.message)

            }
            // println(fromScript)



        }

        this.config.testReport.flush()

    }

}