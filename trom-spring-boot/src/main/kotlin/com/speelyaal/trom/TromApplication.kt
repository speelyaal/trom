package com.speelyaal.trom

import com.speelyaal.trom.config.ConfigLoader
import com.speelyaal.trom.config.TromProperties
import com.speelyaal.trom.runners.TestSuitesRunner
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableConfigurationProperties(TromProperties::class)
class TromApplication

@Autowired
lateinit var configLoader: ConfigLoader


fun main(args: Array<String>) {
	runApplication<TromApplication>(*args)

}




