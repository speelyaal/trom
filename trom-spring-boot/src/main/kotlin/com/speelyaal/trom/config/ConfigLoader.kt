package com.speelyaal.trom.config

import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import javax.annotation.PostConstruct

@Component
class ConfigLoader {

    @Autowired
    lateinit var tromProperties: TromProperties

    var testSuites: HashMap<String, File> = HashMap()
    var testCases: HashMap<String, File> = HashMap()

    lateinit var firefoxDriver: FirefoxDriver

    @PostConstruct
    private fun loadConfigurations(){

        File(tromProperties.rootPath + "/suites").walk().forEach {testSuiteFile ->
            if(testSuiteFile.isFile && testSuiteFile.extension == "kts") {
                this.testSuites[testSuiteFile.nameWithoutExtension] = testSuiteFile
            }
        }

        File(tromProperties.rootPath + "/testcases").walk().forEach {testCaseFile ->
            if(testCaseFile.isFile && testCaseFile.extension == "kts") {
                this.testCases[testCaseFile.nameWithoutExtension] = testCaseFile
            }
        }




    }

}