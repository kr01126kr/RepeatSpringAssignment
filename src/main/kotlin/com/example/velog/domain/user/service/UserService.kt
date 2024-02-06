package com.example.velog.domain.user.service


import com.example.velog.domain.user.dto.TokenInfoDto
import com.example.velog.domain.user.dto.UserLoginDto
import com.example.velog.domain.user.dto.UserResponseDto
import com.example.velog.domain.user.dto.UserSignUpDto
import com.example.velog.domain.user.dto.*

interface UserService {
    fun signUp(userSignUpDto: UserSignUpDto): UserResponseDto

    fun updateUser(userId: Long, userUpdateDto: UserUpdateDto): UserResponseDto

    fun login(userLoginDto: UserLoginDto): TokenInfoDto
}