package br.com.itau.granteeapi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Grantee (

    @Id
    val cpfCnpj: String,

    @Column
    val name: String,

    @Column
    val nickname: String?,

    @Column
    val email: String?,

    @Column
    val phoneNumber: String?
)