package com.example.velog.domain.user.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "토큰을 생성 후 반환할 때 사용하는 객체")
data class TokenInfoDto(
    val grantType: String,
    val accessToken: String,
    val refreshToken: String
)
