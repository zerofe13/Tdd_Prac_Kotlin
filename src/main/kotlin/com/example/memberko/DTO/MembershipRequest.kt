package com.example.memberko.DTO

import com.example.memberko.domain.MembershipType
import jakarta.validation.constraints.Min
import org.jetbrains.annotations.NotNull

class MembershipRequest (
    @field:NotNull
    @field:Min(0)
    val point: Int,
    @field:NotNull
    val membershipType: MembershipType
)