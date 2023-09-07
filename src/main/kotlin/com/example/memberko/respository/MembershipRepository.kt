package com.example.memberko.respository

import com.example.memberko.domain.Membership
import com.example.memberko.domain.MembershipType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MembershipRepository : JpaRepository<Membership , Long>{
    fun findByUserIdAndMembershipType(userId: String, membershipType:MembershipType): Membership?

    fun findAllByUserId(userId: String): List<Membership>
}