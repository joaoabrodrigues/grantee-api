package br.com.itau.granteeapi.repository

import br.com.itau.granteeapi.model.GranteeAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GranteeAccountRepository : JpaRepository<GranteeAccount, Long> {

    fun findByGranteeCpfCnpj(cpf: String): List<GranteeAccount>

}