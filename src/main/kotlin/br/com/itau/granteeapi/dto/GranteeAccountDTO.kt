package br.com.itau.granteeapi.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class GranteeAccountDTO (
    val granteeAccountId: Long?,
    val pixKey: String?,
    val bank: String?,
    val branch: String?,
    val account: String?,
    val digit: String?,
    val accountType: String?
)