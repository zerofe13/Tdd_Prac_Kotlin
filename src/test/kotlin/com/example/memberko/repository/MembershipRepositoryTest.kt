package com.example.memberko.repository

import com.example.memberko.domain.Membership
import com.example.memberko.domain.MembershipType
import com.example.memberko.respository.MembershipRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class MembershipRepositoryTest @Autowired constructor(val membershipRepository: MembershipRepository) {

    @Test
    fun 멤버쉽등록(){
        //given
        val membership:Membership = Membership(
            userId = "test",
            membershipType = MembershipType.LINE,
//            point = 10000
        )
        //when
        val saveMember = membershipRepository.save(membership)
        //then
        assertThat(saveMember).isInstanceOf(Membership::class.java)
        assertThat(saveMember.userId).isEqualTo("test")
    }

    @Test
    fun 멤버쉽존재테스트(){
        //given
        val membership:Membership = Membership(
            userId = "userA",
            membershipType = MembershipType.KAKAO,
            point = 10000
        )
        //when
        membershipRepository.save(membership)
        val findMember = membershipRepository.findByUserIdAndMembershipType("userA", MembershipType.KAKAO)

        //then
        assertThat(findMember.get()).isInstanceOf(Membership::class.java)
        assertThat(findMember.get().membershipType).isEqualTo(MembershipType.KAKAO)
        assertThat(findMember.get().userId).isEqualTo("userA")

    }
    @Test
    fun 멤버쉼조회_0(){
        //given

        //when
        val userId = membershipRepository.findAllByUserId("userId")
        //then
        assertThat(userId).isEmpty()
    }

    @Test
    fun 멤버쉽죄회_2(){
        //given
        val userA: Membership = Membership(
            userId = "user",
            membershipType = MembershipType.NAVER,
            point = 100
        )
        val userB: Membership = Membership(
            userId = "user",
            membershipType = MembershipType.KAKAO,
            point = 10
        )
        membershipRepository.save(userA)
        membershipRepository.save(userB)
        //when
        val findAllByUserId = membershipRepository.findAllByUserId("user")
        //then
        assertThat(findAllByUserId.size).isEqualTo(2)
    }

}