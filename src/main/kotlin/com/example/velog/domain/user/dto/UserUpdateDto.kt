package com.example.velog.domain.user.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

@Schema(description = "유저의 정보를 수정할 때 사용하는 객체")
data class UserUpdateDto(
    @field:NotBlank(message = "이름을 입력해주세요.")
    @field:Pattern(
        regexp = "^[ㄱ-ㅎ|가-힣]{2,10}$",
        message = "이름은 2~10자리 한글이여야 합니다."
    )
    @Schema(description = "회원정보를 수정할 때 사용할 이름", example = "황승현")
    val userName: String,

    @field:Email(message = "올바른 이메일 주소를 입력해주세요")
    @field:NotNull(message = "이메일은 필수 입력값입니다.")
    @Schema(description = "로그인할 때 사용할 이메일", example = "test@gmail.com")
    val userEmail: String
)
