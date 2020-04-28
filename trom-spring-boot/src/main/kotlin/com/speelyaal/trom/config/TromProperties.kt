package com.speelyaal.trom.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "trom")
class TromProperties {

    lateinit var rootPath: String
}