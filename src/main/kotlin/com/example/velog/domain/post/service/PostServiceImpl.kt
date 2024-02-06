package com.example.velog.domain.post.service

import com.example.velog.domain.post.repository.PostRepository
import com.example.velog.domain.post.dto.CreatePostRequestDto
import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.dto.UpdatePostRequestDto
import com.example.velog.domain.post.model.PostEntity
import com.example.velog.domain.exception.ModelNotFoundException
import com.example.velog.domain.post.dto.PostDetailResponseDto
import com.example.velog.domain.user.model.CustomUser
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
) : PostService {

    @Transactional
    override fun createPost(requestDto: CreatePostRequestDto, user: CustomUser): PostResponseDto {
        return postRepository.save(PostEntity.toEntity(requestDto, user)).let { PostEntity.toResponse(it) }
    }

    @Transactional
    override fun getPost(postId: Long): PostDetailResponseDto {
        return postRepository.findByIdOrNull(postId)?.apply { plusView() }
            ?.let { PostEntity.toResponseWithComments(it) }
            ?: throw ModelNotFoundException("post", postId)
    }

    @Transactional
    override fun updatePost(
        postId: Long,
        requestDto: UpdatePostRequestDto
    ): PostResponseDto {
        return postRepository.findByIdOrNull(postId)?.apply { updateEntity(requestDto) }
            ?.let { PostEntity.toResponse(it) }
            ?: throw ModelNotFoundException("post", postId)
    }

    @Transactional
    override fun deletePost(postId: Long) {
        postRepository.findByIdOrNull(postId)
            ?.let { postRepository.delete(it) }
            ?: throw ModelNotFoundException("post", postId)
    }

    override fun getRecentList(
        page: Int,
        size: Int
    ): List<PostResponseDto> {
        return postRepository.findAllByOrderByCreateAtDesc(PageRequest.of(page, size))
            .map { PostEntity.toResponse(it) }
    }

    override fun getTrendList(
        page: Int,
        size: Int
    ): List<PostResponseDto> {
        return postRepository.findAllByOrderByViewsDesc(PageRequest.of(page, size)).map { PostEntity.toResponse(it) }
    }

    override fun getCreatedId(
        postId: Long
    ): Long {
        return postRepository.findByIdOrNull(postId)?.userId ?: throw ModelNotFoundException("post", postId)
    }
}