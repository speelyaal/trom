package com.speelyaal.trom.runners

import com.speelyaal.trom.dsl.TestSuite
import de.swirtz.ktsrunner.objectloader.KtsObjectLoader

class KotlinScriptRunner {

    companion object{
        inline fun <reified T> executeScript(scriptContent: String): T {
            //FIXME: try to have own script runner
            return KtsObjectLoader().load<T>(scriptContent)
        }
    }
}