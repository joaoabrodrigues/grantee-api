package br.com.itau.granteeapi.service

import br.com.itau.granteeapi.converter.toDTO
import br.com.itau.granteeapi.converter.toModel
import br.com.itau.granteeapi.dto.GranteeDTO
import br.com.itau.granteeapi.model.GranteeAccount
import br.com.itau.granteeapi.model.UserGrantees
import br.com.itau.granteeapi.repository.GranteeAccountRepository
import br.com.itau.granteeapi.repository.UserGranteesRepository
import br.com.itau.granteeapi.validation.GranteeValidations
import org.springframework.stereotype.Service

@Service
class GranteeService(private val validations: GranteeValidations,
                     private val userGranteesRepository: UserGranteesRepository,
                     private val granteeAccountRepository: GranteeAccountRepository) {

    fun createGrantee(granteeDTO: GranteeDTO) {
        validations.validate(granteeDTO)

        val granteeAccount = getGranteeAccounts(granteeDTO)
        val userGrantees = getUserGranteesIfExistsOrCreateNew(granteeDTO, granteeAccount)

        userGranteesRepository.save(userGrantees)
    }

    private fun getUserGranteesIfExistsOrCreateNew(granteeDTO: GranteeDTO, granteeAccount: GranteeAccount): UserGrantees {
        val userGrantees = UserGrantees(id = null, userId = granteeDTO.userId, granteeAccount = granteeAccount)

        if (granteeAccount.id != null) {
            val userGrantee =
                userGranteesRepository.findByUserIdAndGranteeAccountId(granteeDTO.userId, granteeAccount.id)

            userGrantees.id = userGrantee?.id
        }
        return userGrantees
    }

    private fun getGranteeAccounts(granteeDTO: GranteeDTO): GranteeAccount {
        val existentAccounts = granteeAccountRepository.findByGranteeCpfCnpj(granteeDTO.cpfCnpj)

        if (existentAccounts.isNullOrEmpty()) {
            return granteeDTO.granteeAccount.toModel(granteeDTO.toModel())
        }

        return findDTOAccountInExistentAccounts(existentAccounts, granteeDTO)
    }

    private fun findDTOAccountInExistentAccounts(granteeAccounts: List<GranteeAccount>, granteeDTO: GranteeDTO): GranteeAccount {
        val pixKey = granteeDTO.granteeAccount.pixKey

        val bank = granteeDTO.granteeAccount.bank
        val account = granteeDTO.granteeAccount.account
        val accountType = granteeDTO.granteeAccount.accountType
        val branch = granteeDTO.granteeAccount.branch
        val digit = granteeDTO.granteeAccount.digit

        if (!pixKey.isNullOrBlank()) {
            println("pixKey $pixKey cpfCpnj ${granteeDTO.cpfCnpj}")
            println("granteeACcounts $granteeAccounts")
            return granteeAccounts.first { it.pixKey == pixKey && it.grantee.cpfCnpj == granteeDTO.cpfCnpj }
        }

        return granteeAccounts.first {
            it.bank == bank &&
            it.account == account &&
            it.accountType == accountType &&
            it.branch == branch &&
            it.digit == digit &&
            it.grantee.cpfCnpj == granteeDTO.cpfCnpj
        }
    }

    fun findGranteesByUser(userId: Long) : List<GranteeDTO> {
        val userGrantees = userGranteesRepository.findByUserId(userId)

        return userGrantees.map {
            it.granteeAccount.grantee.toDTO(it.granteeAccount, it.userId)
        }.toList()
    }
}