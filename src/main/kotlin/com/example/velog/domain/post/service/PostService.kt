package com.example.velog.domain.post.service

import com.example.velog.domain.post.dto.CreatePostRequestDto
import com.example.velog.domain.post.dto.PostDetailResponseDto
import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.dto.UpdatePostRequestDto
import com.example.velog.domain.user.model.CustomUser

interface PostService {
    fun createPost(requestDto: CreatePostRequestDto, user: CustomUser): PostResponseDto
    fun getPost(postId: Long): PostDetailResponseDto
    fun updatePost(postId: Long, requestDto: UpdatePostRequestDto): PostResponseDto
    fun deletePost(postId: Long)
    fun getRecentList(page: Int, size: Int): List<PostResponseDto>
    fun getTrendList(page: Int, size: Int): List<PostResponseDto>
    fun getCreatedId(postId: Long): Long
}