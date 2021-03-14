package br.com.itau.granteeapi.converter

import br.com.itau.granteeapi.dto.GranteeDTO
import br.com.itau.granteeapi.dto.GranteeAccountDTO
import br.com.itau.granteeapi.model.Grantee
import br.com.itau.granteeapi.model.GranteeAccount

fun GranteeDTO.toModel() = Grantee(
    cpfCnpj, name, nickname, email, phoneNumber
)

fun GranteeAccountDTO.toModel(grantee: Grantee) = GranteeAccount(
    id = null,
    grantee = grantee,
    pixKey, bank, branch, account, digit, accountType
)