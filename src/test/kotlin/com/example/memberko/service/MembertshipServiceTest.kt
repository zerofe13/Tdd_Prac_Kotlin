package com.example.memberko.service

import com.example.memberko.domain.Membership
import com.example.memberko.domain.MembershipType
import com.example.memberko.respository.MembershipRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
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
        val result: MembershipException = assertThrows(MembershipException::class,()->membershipService.addMembership(userA,membershipType,point))
        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATE_MEMBERSHIP_REGISTER)
    }
}