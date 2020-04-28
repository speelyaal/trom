package com.speelyaal.trom.runners

import com.speelyaal.trom.config.ConfigLoader
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


                KtsObjectLoader().load<Unit>(scriptContent)!!
            }catch (exception: IllegalArgumentException){
                println("error after executing script")
            }
           // println(fromScript)



        }
    }

}