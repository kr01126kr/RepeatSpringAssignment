package com.example.velog.domain.post.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "응답을 전달하는 객체")
data class PostResponseDto(
    val postId: Long,
    val title: String,
    val content: String,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime,
    val createName: String,
    val updateName: String,
    val views: Int
)