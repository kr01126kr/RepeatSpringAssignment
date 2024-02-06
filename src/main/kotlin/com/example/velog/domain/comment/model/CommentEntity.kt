package com.example.velog.domain.comment.model

import com.example.velog.domain.comment.dto.UpdateCommentArguments
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
//@Table(name = "Comment")
@Table(name = "Comment2")
class CommentEntity(

    @Column
    var content: String,

    @Column
    val postId: Long,

    @Column
    val userId: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentId: Long? = null

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

    fun changeUpdateComment(updateCommentArguments: UpdateCommentArguments) {
        this.content = updateCommentArguments.content
    }

}