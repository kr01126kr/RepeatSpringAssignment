package com.example.velog.domain.post.dto

import com.example.velog.domain.comment.model.CommentEntity
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "응답을 전달하는 객체")
data class PostDetailResponseDto(
    val postId: Long,
    val title: String,
    val content: String,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime,
    val createName: String,
    val updateName: String,
    val views: Int,
    val comments: MutableList<CommentEntity>
)