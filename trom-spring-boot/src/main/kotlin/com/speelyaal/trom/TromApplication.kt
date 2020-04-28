package com.speelyaal.trom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TromApplication

fun main(args: Array<String>) {
	runApplication<TromApplication>(*args)
}
