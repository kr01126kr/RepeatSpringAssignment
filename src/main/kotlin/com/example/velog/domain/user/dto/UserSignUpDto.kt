package com.example.velog.domain.user.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

@Schema(description = "회원가입할 때 입력한 정보를 전달하는 객체")
data class UserSignUpDto(
    @field:NotBlank(message = "이름을 입력해주세요.")
    @field:Pattern(
        regexp = "^[ㄱ-ㅎ|가-힣]{2,10}$",
        message = "이름은 2~10자리 한글이여야 합니다."
    )
    @Schema(description = "회원가입할 때 사용할 이름", example = "황승현")
    val userName: String,

    @field:Email(message = "올바른 이메일 주소를 입력해주세요")
    @field:NotNull(message = "이메일은 필수 입력값입니다.")
    @Schema(description = "회원가입할 때 사용할 이메일", example = "test@gmail.com")
    val userEmail: String,

    @field:NotBlank(message = "비밀번호를 입력해주세요")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9!@#\$%^&*]{8,25}$",
        message = "비밀번호는 영어, 숫자, 특수문자로 이루어진 8~25자리 문자열로 입력해야 합니다."
    )
    @Schema(description = "회원가입할 때 사용할 비밀번호", example = "12345678")
    val password: String
)