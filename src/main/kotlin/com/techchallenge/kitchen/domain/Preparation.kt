package com.techchallenge.kitchen.domain

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "preparations")
data class Preparation(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    val orderId: String,
    val dueDate: String,
    val status: String
)
