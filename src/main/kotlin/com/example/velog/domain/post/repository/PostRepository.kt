package com.example.velog.domain.post.repository

import com.example.velog.domain.post.model.PostEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findAllByOrderByCreateAtDesc(pageable: Pageable): List<PostEntity>
    fun findAllByOrderByViewsDesc(pageable: Pageable): List<PostEntity>
}