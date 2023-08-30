package com.example.memberko.DTO

import com.example.memberko.domain.MembershipType

class MembershipResponse (
    val id: Long? = null,
    val userId: String,
    val membershipType: MembershipType,
    val point: Int
)