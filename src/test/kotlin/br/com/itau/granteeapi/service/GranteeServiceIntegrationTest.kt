package br.com.itau.granteeapi.service

import br.com.itau.granteeapi.dto.GranteeAccountDTO
import br.com.itau.granteeapi.dto.GranteeDTO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GranteeServiceIntegrationTest {

    @Autowired
    private lateinit var granteeService: GranteeService

    @BeforeAll
    internal fun setUp() {
        granteeService.createGrantee(buildDTO())
    }

    @Test
    fun `should create grantee with no errors`() {
        val findResult = granteeService.findGranteesByUser(1)

        assertNotNull(findResult)
        assertEquals(1, findResult.size)
        assertEquals(1, findResult.first().userId)
        assertEquals("12345678901", findResult.first().cpfCnpj)
        assertEquals("Joao Rodrigues", findResult.first().name)
    }

    private fun buildDTO(): GranteeDTO {
        return GranteeDTO(
            userId = 1,
            cpfCnpj = "12345678901",
            name = "Joao Rodrigues",
            nickname = "",
            email = "",
            phoneNumber = "",
            granteeAccount = GranteeAccountDTO(
                granteeAccountId = 1,
                pixKey = "123456789",
                bank = "",
                branch = "",
                account = "",
                digit = "",
                accountType = ""
            )
        )
    }
}