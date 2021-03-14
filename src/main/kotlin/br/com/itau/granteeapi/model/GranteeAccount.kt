package br.com.itau.granteeapi.model

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table
data class GranteeAccount (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grantee_account_seq")
    @SequenceGenerator(name = "grantee_account_seq", sequenceName = "grantee_account_seq", allocationSize = 1)
    val id: Long?,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val grantee: Grantee,

    @Column
    val pixKey: String?,

    @Column
    val bank: String?,

    @Column
    val branch: String?,

    @Column
    val account: String?,

    @Column
    val digit: String?,

    @Column
    val accountType: String?
)