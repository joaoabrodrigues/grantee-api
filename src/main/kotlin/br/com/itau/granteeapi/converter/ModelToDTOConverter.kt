package br.com.itau.granteeapi.converter

import br.com.itau.granteeapi.dto.GranteeDTO
import br.com.itau.granteeapi.dto.GranteeAccountDTO
import br.com.itau.granteeapi.model.Grantee
import br.com.itau.granteeapi.model.GranteeAccount

fun Grantee.toDTO(granteeAccount: GranteeAccount, userId: Long) = GranteeDTO(
    granteeAccount = granteeAccount.toDTO(),
    email = email,
    name = name,
    cpfCnpj = cpfCnpj,
    nickname = nickname,
    phoneNumber = phoneNumber,
    userId = userId
)

fun GranteeAccount.toDTO() = GranteeAccountDTO(
    id, pixKey, bank, branch, account, digit, accountType
)