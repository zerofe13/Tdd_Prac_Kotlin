package com.example.memberko.service

import com.example.memberko.DTO.MembershipResponse
import com.example.memberko.domain.Membership
import com.example.memberko.domain.MembershipType
import com.example.memberko.exception.MembershipErrorResult
import com.example.memberko.exception.MembershipException
import com.example.memberko.respository.MembershipRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class MembershipService (private val membershipRepository: MembershipRepository) {

    fun addMembership(userId: String, membershipType: MembershipType, point: Int): MembershipResponse{
        val findMember = membershipRepository.findByUserIdAndMembershipType(userId, membershipType) ?: throw MembershipException(MembershipErrorResult.DUPLICATE_MEMBERSHIP_REGISTER)

        val result: Membership = membershipRepository.save(
            Membership(
                userId = userId,
                membershipType = membershipType,
                point = point
            )
        )

        return MembershipResponse(
            userId = result.userId,
            membershipType = result.membershipType,
            point = result.point
        )
    }

    fun findAllByUserID(userId: String): List<MembershipResponse> {
        return membershipRepository.findAllByUserId(userId).asSequence()
            .map { it -> MembershipResponse(
                userId = it.userId,
                membershipType = it.membershipType,
                point = it.point
            ) }.toList()
    }

    fun getMembership(Id: Long, userId: String): MembershipResponse{
        val membership = membershipRepository.findByIdOrNull(Id)
            ?: throw MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND)

        if (membership.userId == userId){
            throw MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND)
        }

        return MembershipResponse(
            id = membership.id,
            userId = membership.userId,
            membershipType = membership.membershipType,
            point = membership.point
        )
    }
}