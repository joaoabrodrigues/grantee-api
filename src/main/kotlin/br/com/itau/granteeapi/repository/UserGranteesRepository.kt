package br.com.itau.granteeapi.repository

import br.com.itau.granteeapi.model.UserGrantees
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserGranteesRepository : JpaRepository<UserGrantees, Long> {

    fun findByUserId(userId: Long) : List<UserGrantees>
    fun findByUserIdAndGranteeAccountId(userId: Long, granteeAccountId: Long) : UserGrantees?
}