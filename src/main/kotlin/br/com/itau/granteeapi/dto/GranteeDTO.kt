package br.com.itau.granteeapi.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class GranteeDTO (
    val userId: Long,
    val cpfCnpj: String,
    val name: String,
    val nickname: String?,
    val email: String?,
    val phoneNumber: String?,
    val granteeAccount: GranteeAccountDTO
)