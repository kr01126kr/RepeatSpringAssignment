package com.example.velog.domain.user.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "응답을 전달하는 객체")
data class UserResponseDto(
    val userId: Long,
    val userName: String,
    val userEmail: String,
)