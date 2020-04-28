package com.speelyaal.trom.runners

import com.speelyaal.trom.config.ConfigLoader
import com.speelyaal.trom.dsl.TestCase
import com.speelyaal.trom.dsl.TestSuite
import com.speelyaal.trom.dsl.testSuite
import de.swirtz.ktsrunner.objectloader.KtsObjectLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import javax.annotation.PostConstruct
import javax.script.ScriptEngineManager

@Component
class TestSuitesRunner {

    @Autowired
    lateinit var configLoader: ConfigLoader



    @PostConstruct
    fun executeTestSuites(){
        println("execution of test suites called")
        this.configLoader.testSuites.forEach { suite ->

            println("suite 1" + suite.key)
            println("suite 1 File" + suite.value)

          //  KtsObjectLoader().load<Unit>(suite.value.readText())
            val scriptContent = suite.value.readText()
            println(scriptContent)
            try {
              val testSuite : TestSuite =  KtsObjectLoader().load<TestSuite>(scriptContent)

                testSuite.testCases.forEach{ case->


                        var testCaseToRun = this.configLoader.testCases[case]!!.readText()
                        println(testCaseToRun)
                        val testCase : TestCase =  KtsObjectLoader().load<TestCase>(testCaseToRun)
                        println("Test case name is  ${testCase.name}")

                        testCase.testSteps.forEach {testStep ->
                            println("Test step anem is ${testStep.name}")

                            println("Test step Type ${testStep.action.type}")
                        }



                }


                testSuite.browsers.forEach{
                    println("Browsers $it")
                }

            }catch (exception: IllegalArgumentException){
                println("error after executing script")
            }
           // println(fromScript)



        }
    }

}