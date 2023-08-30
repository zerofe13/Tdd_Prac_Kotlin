package com.example.memberko.service

import com.example.memberko.DTO.MembershipResponse
import com.example.memberko.domain.Membership
import com.example.memberko.domain.MembershipType
import com.example.memberko.exception.MembershipErrorResult
import com.example.memberko.exception.MembershipException
import com.example.memberko.respository.MembershipRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class MembertshipServiceTest{

    @InjectMocks
    lateinit var membershipRepository: MembershipRepository

    @Mock
    lateinit var membershipService: MembershipService

    val userA: String = "userA"

    val membershipType: MembershipType = MembershipType.NAVER

    val point: Int = 100

    fun membership(): Membership = Membership(
        id = -1L,
        userId = userA,
        membershipType = membershipType,
        point = point
    )

    @Test
    fun 멤버쉽등록실패_이미존재함(){
        //given
        doReturn(Optional.of(membership())).`when`(membershipRepository).findByUserIdAndMembershipType(userA,membershipType)
        //when
        val result: MembershipException = assertThrows { membershipService.addMembership(userA,membershipType,point) }
        //then
        assertThat(result.errorResult).isEqualTo(MembershipErrorResult.DUPLICATE_MEMBERSHIP_REGISTER)
    }

    @Test
    fun 멤버쉽등록_성공(){
        //given
        doReturn(Optional.ofNullable(null)).`when`(membershipRepository).findByUserIdAndMembershipType(userA,membershipType)
        doReturn(membership()).`when`(membershipRepository).save(any(Membership::class.java))
        //when
        val saveMembership: MembershipResponse = membershipService.addMembership(userA, membershipType, point)
        //then
        assertThat(saveMembership).isInstanceOf(Membership::class.java)
    }

    @Test
    fun 멤버쉽목록조회(){
        //given
        doReturn(arrayOf(Membership(
            userId = "user",
            membershipType = membershipType,
            point = 1
        )
            , Membership(
                userId = "user",
                membershipType = membershipType,
                point = 2
            )
            , Membership(
                userId = "user",
                membershipType = membershipType,
                point = 3
            )
        )).`when`(membershipRepository).findAllByUserId("user")
        //when
        val result: List<MembershipResponse> = membershipService.findAllByUserID("user")
        //then
        assertThat(result.size).isEqualTo(3)
    }
    @Test
    fun 멤버쉽조회실패_해당멤버쉽없음(){
        //given
        doReturn(null).`when`(membershipRepository).findByIdOrNull(-1L)
        //when
        val membershipException: MembershipException = assertThrows { membershipService.getMembership(-1L,userA) }
        //then
        assertThat(membershipException.errorResult).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND)
    }

    @Test
    fun 멤버쉽조회실패_잘못된userId(){
        //given
        doReturn(membership()).`when`(membershipRepository).findByIdOrNull(-1L)
        //when
        val membershipException: MembershipException = assertThrows { membershipService.getMembership(-1L,userA) }
        //then
        assertThat(membershipException.errorResult).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND)
    }

    @Test
    fun 조회성공(){
        //given
        doReturn(membership()).`when`(membershipRepository).findByIdOrNull(-1L)
        //when
        val membership: MembershipResponse = membershipService.getMembership(-1L, userA)
        //then
        assertThat(membership.id).isEqualTo(-1L)
        assertThat(membership.userId).isEqualTo(userA)
    }

}