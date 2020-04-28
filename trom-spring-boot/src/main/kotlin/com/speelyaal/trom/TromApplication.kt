package com.speelyaal.trom

import com.speelyaal.trom.config.TromProperties
import com.speelyaal.trom.runners.TestSuitesRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableConfigurationProperties(TromProperties::class)
class TromApplication


fun main(args: Array<String>) {
	runApplication<TromApplication>(*args)


}

