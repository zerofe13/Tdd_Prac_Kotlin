package com.example.memberko.controller

import com.example.memberko.DTO.MembershipRequest
import com.example.memberko.DTO.MembershipResponse
import com.example.memberko.domain.USER_ID_HEADER
import com.example.memberko.service.MembershipService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class MembershipController (private val membershipService : MembershipService ) {
    @PostMapping("/api/v1/membership")
    fun addMembership(@RequestHeader(USER_ID_HEADER) userId: String,
                      @RequestBody @Valid membershipRequest: MembershipRequest) : ResponseEntity<MembershipResponse>{
        val response : MembershipResponse =
            membershipService.addMembership(userId, membershipRequest.membershipType, membershipRequest.point)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
    @GetMapping("/api/v1/membership")
    fun getMembershipList(@RequestHeader(USER_ID_HEADER) userId: String):ResponseEntity<List<MembershipResponse>>{
        val findAllByUserID = membershipService.findAllByUserID(userId)
        return ResponseEntity.ok(findAllByUserID)
    }
    @GetMapping("/api/v1/membership/{id}")
    fun getMembership(@RequestHeader(USER_ID_HEADER) userId: String,@PathVariable id: Long):ResponseEntity<MembershipResponse>{
        return ResponseEntity.ok(membershipService.getMembership(id,userId))
    }

}