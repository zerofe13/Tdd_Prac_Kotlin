package com.example.memberko.DTO

import com.example.memberko.domain.MembershipType
import org.jetbrains.annotations.NotNull

class MembershipRequest (
    val point: Int,
    val membershipType: MembershipType
)