package com.example.velog.domain.user.repository

import com.example.velog.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun existsByEmail(email:String):Boolean
    fun findByEmail(email: String): UserEntity?
}