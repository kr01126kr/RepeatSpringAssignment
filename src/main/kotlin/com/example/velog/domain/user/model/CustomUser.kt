package com.example.velog.domain.user.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

class CustomUser private constructor(
    val email: String,
    val name: String,
    userName: String,
    password: String,
    authorities: Collection<GrantedAuthority>
) : User(userName, password, authorities) {
    companion object {
        fun createUserDetails(userEntity: UserEntity): UserDetails {
            val grantedAuthority: MutableList<SimpleGrantedAuthority> = mutableListOf()
            grantedAuthority.add(SimpleGrantedAuthority("ROLE_${userEntity.role}"))
            return CustomUser(
                email = userEntity.email,
                name = userEntity.userName, //name: 로그인을 시도한 사용자의 이름
                userName = userEntity.userId!!.toString(), //userName: 유저를 대표하는 값이 들어감
                password = userEntity.password,
                authorities = grantedAuthority
            )
        }

        fun createUserDetails(
            email: String,
            name: String,
            userName: String,
            password: String,
            authorities: Collection<GrantedAuthority>
        ): UserDetails {
            return CustomUser(
                email = email,
                name = name,
                userName = userName,
                password = password,
                authorities = authorities
            )
        }
    }
}