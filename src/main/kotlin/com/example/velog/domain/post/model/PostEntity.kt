package com.example.velog.domain.post.model

import com.example.velog.domain.comment.model.CommentEntity
import com.example.velog.domain.post.dto.CreatePostRequestDto
import com.example.velog.domain.post.dto.PostDetailResponseDto
import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.dto.UpdatePostRequestDto
import com.example.velog.domain.user.model.CustomUser
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
//@Table(name = "post")
@Table(name = "post2")
class PostEntity private constructor(
    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "user_id")
    val userId: Long,

    @OneToMany(mappedBy = "postId", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<CommentEntity> = mutableListOf()
) {
    @Id //PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var postId: Long? = null

    @CreatedDate
    @Column(name = "create_at")
    var createAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null

    @CreatedBy
    @Column(name = "create_name")
    var createName: String? = null

    @LastModifiedBy
    @Column(name = "update_name")
    var updateName: String? = null

    @Column(name = "views")
    var views: Int = 0

    fun plusView() = this.views++

    fun updateEntity(requestDto: UpdatePostRequestDto) {
        this.title = requestDto.title
        this.content = requestDto.content
    }

    companion object {
        fun toEntity(
            requestDto: CreatePostRequestDto,
            user: CustomUser
        ): PostEntity {
            return PostEntity(
                title = requestDto.title,
                content = requestDto.content,
                userId = user.username.toLong()
            )
        }

        fun toResponse(
            postEntity: PostEntity
        ): PostResponseDto {
            return PostResponseDto(
                postId = postEntity.postId!!,
                title = postEntity.title,
                content = postEntity.content,
                createAt = postEntity.createAt!!,
                updateAt = postEntity.updateAt!!,
                createName = postEntity.createName!!,
                updateName = postEntity.updateName!!,
                views = postEntity.views
            )
        }

        fun toResponseWithComments(
            postEntity: PostEntity
        ): PostDetailResponseDto {
            return PostDetailResponseDto(
                postId = postEntity.postId!!,
                title = postEntity.title,
                content = postEntity.content,
                createAt = postEntity.createAt!!,
                updateAt = postEntity.updateAt!!,
                createName = postEntity.createName!!,
                updateName = postEntity.updateName!!,
                views = postEntity.views,
                comments = postEntity.comments
            )
        }
    }
}