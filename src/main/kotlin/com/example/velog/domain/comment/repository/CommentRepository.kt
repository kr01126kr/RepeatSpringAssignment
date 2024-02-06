package com.example.velog.domain.comment.repository

import com.example.velog.domain.comment.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long> {
    fun findAllByPostId(postId: Long): List<CommentEntity>
    fun findByPostIdAndCommentId(postId: Long, commentId: Long): CommentEntity?

}