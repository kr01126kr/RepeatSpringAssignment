package com.example.velog.domain.comment.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*

@Schema(description = "댓글을 작성할 때 입력한 정보를 전달하는 객체")
data class CreatCommentArguments(
    @field:NotBlank(message = "댓글을 입력해주세요.")
    @Size(min = 1, max = 200, message = "댓글은 1자 이상 200자 이하로 작성해주세요")
    @Schema(description = "작성한 댓글 내용", example = "댓글 내용")
    val content: String
)