package com.example.velog.domain.exception

data class AuthException(
    override val message: String
) : RuntimeException(message)