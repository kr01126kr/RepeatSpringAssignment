package com.example.velog.domain.comment.dto

import com.example.velog.domain.comment.model.CommentEntity
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "게시글을 수정할 때 입력한 정보를 전달하는 객체")
data class CommentDto(
    val id: Long?,
    val content: String,
    val createAt: LocalDateTime?,
    val updateAt: LocalDateTime?,
    val updateName: String?,
    val createName: String?,
    val postId: Long?
) {
    companion object {
        fun from(comment: CommentEntity): CommentDto {
            return CommentDto(
                id = comment.commentId,
                content = comment.content,
                createAt = comment.createAt,
                updateName = comment.updateName,
                updateAt = comment.updateAt,
                createName = comment.createName,
                postId = comment.postId
            )
        }
    }
}