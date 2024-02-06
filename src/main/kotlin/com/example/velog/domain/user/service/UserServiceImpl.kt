package com.example.velog.domain.user.service

import com.example.velog.domain.exception.ModelNotFoundException
import com.example.velog.domain.user.dto.*
import com.example.velog.domain.user.model.UserEntity
import com.example.velog.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder

) : UserService {
    @Transactional
    override fun signUp(userSignUpDto: UserSignUpDto): UserResponseDto {
        if (userRepository.existsByEmail(userSignUpDto.userEmail)) throw IllegalStateException("가입된 이메일 입니다.")
        return UserEntity.toResponse(userRepository.save(UserEntity.toUserEntity(userSignUpDto, encoder)))
    }

    override fun login(userLoginDto: UserLoginDto): TokenInfoDto {
        val authenticationToken = UsernamePasswordAuthenticationToken(userLoginDto.email, userLoginDto.password)
        return authenticationManagerBuilder
            .`object`
            .authenticate(authenticationToken)
            .let { tokenProvider.createToken(it) }
    }

    @Transactional
    override fun updateUser(
        userId: Long,
        userUpdateDto: UserUpdateDto
    ): UserResponseDto {
        return userRepository.findByIdOrNull(userId)
            ?.apply { updateUserprofile(userUpdateDto) }
            ?.let { UserEntity.toResponse(userRepository.saveAndFlush(it)) }
            ?: throw ModelNotFoundException("user", userId)
    }
}