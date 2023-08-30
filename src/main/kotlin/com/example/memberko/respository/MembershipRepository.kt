package com.example.memberko.respository

import com.example.memberko.domain.Membership
import com.example.memberko.domain.MembershipType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MembershipRepository : JpaRepository<Membership , Long>{
    override fun findById(id: Long): Optional<Membership>

    fun findByUserIdAndMembershipType(userId: String, membershipType:MembershipType): Optional<Membership>

    fun findAllByUserId(userId: String): List<Membership>
}