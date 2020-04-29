package com.speelyaal.trom.config

import com.speelyaal.trom.dsl.TestCase
import com.speelyaal.trom.dsl.TestSuite
import com.speelyaal.trom.runners.KotlinScriptRunner
import com.speelyaal.trom.runners.TestSuitesRunner
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import javax.annotation.PostConstruct

@Component
class ConfigLoader {

    private val LOG: Logger = LogManager.getLogger(ConfigLoader::class.java)

    @Autowired
    lateinit var tromProperties: TromProperties

    var testSuites: HashMap<String, TestSuite> = HashMap()
    var testCases: HashMap<String, TestCase> = HashMap()

    lateinit var firefoxDriver: FirefoxDriver

    @PostConstruct
    private fun loadConfigurations(){

        File(tromProperties.rootPath + "/suites").walk().forEach {testSuiteFile ->
            if(testSuiteFile.isFile && testSuiteFile.extension == "kts") {


                LOG.info("Loading Test Suite Script : ${testSuiteFile.name}")
                val testSuite: TestSuite = KotlinScriptRunner.executeScript(testSuiteFile.readText())
           //     this.testSuites[testSuiteFile.nameWithoutExtension] = testSuite
                this.testSuites[testSuite.name] = testSuite

            }
        }

        File(tromProperties.rootPath + "/testcases").walk().forEach {testCaseFile ->
            if(testCaseFile.isFile && testCaseFile.extension == "kts") {

                LOG.info("Loading Test Case Script : ${testCaseFile.name}")
                val testCase: TestCase = KotlinScriptRunner.executeScript(testCaseFile.readText())
                this.testCases[testCaseFile.nameWithoutExtension] = testCase
                this.testCases[testCase.name] = testCase
            }
        }




    }

}