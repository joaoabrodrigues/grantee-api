package br.com.itau.granteeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class GranteeApiApplication

fun main(args: Array<String>) {
	runApplication<GranteeApiApplication>(*args)
}
