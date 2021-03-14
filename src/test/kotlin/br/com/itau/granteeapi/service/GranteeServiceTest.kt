package br.com.itau.granteeapi.service

import br.com.itau.granteeapi.converter.toModel
import br.com.itau.granteeapi.dto.GranteeAccountDTO
import br.com.itau.granteeapi.dto.GranteeDTO
import br.com.itau.granteeapi.model.UserGrantees
import br.com.itau.granteeapi.repository.GranteeAccountRepository
import br.com.itau.granteeapi.repository.UserGranteesRepository
import br.com.itau.granteeapi.validation.GranteeValidations
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString

internal class GranteeServiceTest {

    private val granteeAccountRepository: GranteeAccountRepository = mock()
    private val userGranteesRepository: UserGranteesRepository = mock()
    private val validations: GranteeValidations = mock()
    private val granteeService = GranteeService(validations, userGranteesRepository, granteeAccountRepository)

    @Test
    fun `should create grantee with no errors`() {
        val granteeDTO = buildDTO()

        assertDoesNotThrow {
            granteeService.createGrantee(granteeDTO)
        }

        verify(granteeAccountRepository, times(1)).findByGranteeCpfCnpj(anyString())
        verify(userGranteesRepository, times(1)).save(any())
    }

    @Test
    fun `should find grantee with no errors`() {
        whenever(userGranteesRepository.findByUserId(anyLong())).thenReturn(listOf(buildModel()))

        val findResult = granteeService.findGranteesByUser(1)

        assertNotNull(findResult)
        assertEquals(1, findResult.size)
        assertEquals(1, findResult.first().userId)
        assertEquals("12345678901", findResult.first().cpfCnpj)

        verify(userGranteesRepository, times(1)).findByUserId(anyLong())
    }

    private fun buildModel() : UserGrantees {
        val granteeDTO = buildDTO()
        return UserGrantees(
            id = 1,
            userId = 1,
            granteeAccount = granteeDTO.granteeAccount.toModel(granteeDTO.toModel())
        )
    }

    private fun buildDTO(): GranteeDTO {
        return GranteeDTO(
            userId = 1,
            cpfCnpj = "12345678901",
            name = "",
            nickname = "",
            email = "",
            phoneNumber = "",
            granteeAccount = GranteeAccountDTO(
                granteeAccountId = 1,
                pixKey = "",
                bank = "",
                branch = "",
                account = "",
                digit = "",
                accountType = ""
            )
        )
    }
}