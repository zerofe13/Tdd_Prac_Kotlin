package com.example.memberko.domain

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class Membership (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Enumerated(EnumType.STRING)
    var membershipType: MembershipType,

    @Column(nullable = false)
    var userId: String,

    @Column(nullable = false)
    var point: Int = 0,

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    var creatAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(length = 20)
    var updateAt: LocalDateTime? = null

) {
    override fun toString(): String {
        return "Membership(id=$id, membershipType=$membershipType, userId='$userId', point=$point, creatAt=$creatAt, updateAt=$updateAt)"
    }
}