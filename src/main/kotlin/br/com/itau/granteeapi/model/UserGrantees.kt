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
data class UserGrantees (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_grantees_seq")
    @SequenceGenerator(name = "user_grantees_seq", sequenceName = "user_grantees_seq", allocationSize = 1)
    var id: Long?,

    @Column
    val userId: Long,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val granteeAccount: GranteeAccount
)
