package com.example.velog.domain.user.controller

import com.example.velog.domain.exception.ForbiddenException
import com.example.velog.domain.user.dto.*
import com.example.velog.domain.user.dto.TokenInfoDto
import com.example.velog.domain.user.dto.UserLoginDto
import com.example.velog.domain.user.dto.UserResponseDto
import com.example.velog.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import com.example.velog.domain.user.dto.UserSignUpDto
import com.example.velog.domain.user.model.CustomUser
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "users", description = "사용자 API")
@RestController("users")
class UserController(
    private val userService: UserService
) {
    @Operation(summary = "회원가입", description = "회원가입.")
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody userSignUpDto: UserSignUpDto): ResponseEntity<UserResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(userSignUpDto))
    }

    @Operation(summary = "사용자 정보 수정", description = "프로필 정보 수정")
    @PutMapping("/{userId}")
    fun userUpdate(
        @PathVariable userId: Long,
        @Valid @RequestBody userUpdateDto: UserUpdateDto,
        @AuthenticationPrincipal user: CustomUser
    ): ResponseEntity<UserResponseDto> {
        if (user.username.toLong() != userId) throw ForbiddenException("You don't have permission to access.")
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateUser(userId, userUpdateDto))
    }

    @Operation(summary = "로그인", description = "사용자가 입력한 아이디와 비밀번호로 로그인을 시도.")
    @PostMapping("/login")
    fun login(@Valid @RequestBody userLoginDto: UserLoginDto): ResponseEntity<TokenInfoDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(userLoginDto))
    }

}
