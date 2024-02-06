package com.example.velog.domain.comment.service

import com.example.velog.domain.comment.dto.CommentDto
import com.example.velog.domain.comment.dto.CreatCommentArguments
import com.example.velog.domain.comment.dto.UpdateCommentArguments
import com.example.velog.domain.user.model.CustomUser

interface CommentService {
    fun createComment(creatCommentArguments: CreatCommentArguments, postId: Long, user: CustomUser): CommentDto
    fun findByCommentId(commentId: Long): CommentDto
    fun findAllCommentList(postId: Long): List<CommentDto>
    fun updateComment(updateCommentArguments: UpdateCommentArguments, postId: Long, commentId: Long): CommentDto
    fun deleteComment(postId: Long, commentId: Long)

    fun getCreatedId(postId: Long, commentId: Long): Long
}