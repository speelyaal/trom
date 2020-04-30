package com.speelyaal.trom.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "trom")
class TromProperties {



    lateinit var rootPath: String
    lateinit var firefoxDriverPath: String
    lateinit var chromeDriverPath: String
    lateinit var safariDriverPath: String
    lateinit var edgeDriverPath: String

    /*class Browser{
        var driverPath: String =""
        var driverName: String= ""
    }*/

}