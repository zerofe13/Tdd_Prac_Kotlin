package com.example.memberko.controller

import com.example.memberko.service.MembershipService
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder

@ExtendWith(MockitoExtension::class)
class MembershipControllerTest {
    @InjectMocks
    lateinit var membershipController: MembershipController
    @Mock
    lateinit var membershipService: MembershipService
    lateinit var mockMvc: MockMvc
    lateinit var gson: Gson

    @BeforeEach
    fun init(){

    }


}