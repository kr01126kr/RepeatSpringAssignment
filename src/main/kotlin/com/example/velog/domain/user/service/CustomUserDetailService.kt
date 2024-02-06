package com.example.velog.domain.user.service

import com.example.velog.domain.user.model.CustomUser
import com.example.velog.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails { //username = 유저가 입력한 이메일 아이디
        return CustomUser.createUserDetails(
            userRepository.findByEmail(username) ?: throw UsernameNotFoundException("User $username not found")
        )
    }
}