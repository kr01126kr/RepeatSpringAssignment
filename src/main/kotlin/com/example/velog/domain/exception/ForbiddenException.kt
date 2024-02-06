package com.example.velog.domain.exception

data class ForbiddenException(
    override val message: String
) : RuntimeException(message)

