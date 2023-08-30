package com.example.memberko.exception

import org.springframework.http.HttpStatus

enum class MembershipErrorResult(val httpStatus: HttpStatus, val message: String) {
    DUPLICATE_MEMBERSHIP_REGISTER(HttpStatus.BAD_REQUEST,"Duplicated Membership Register Request"),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,"Unknown exception"),
    MEMBERSHIP_NOT_FOUND(HttpStatus.NOT_FOUND,"MemberShip not found")
}