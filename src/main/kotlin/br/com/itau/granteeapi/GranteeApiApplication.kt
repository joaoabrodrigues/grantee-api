package br.com.itau.granteeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GranteeApiApplication

fun main(args: Array<String>) {
	runApplication<GranteeApiApplication>(*args)
}
