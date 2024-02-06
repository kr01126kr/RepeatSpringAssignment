package com.example.velog.domain.post.dto

import com.example.velog.domain.user.model.CustomUser
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*
import org.springframework.security.core.annotation.AuthenticationPrincipal

@Schema(description = "게시글을 작성할 때 입력한 정보를 전달하는 객체")
data class CreatePostRequestDto(
    @field:NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 200, message = "제목은 1자 이상 200자 이하로 작성해주세요")
    @Schema(description = "게시글 제목", example = "제목")
    val title: String,

    @field:NotBlank(message = "본문을 입력해주세요.")
    @Size(min = 1, max = 5000, message = "본문은 1자 이상 5000자 이하로 작성해주세요")
    @Schema(description = "게시글 내용", example = "내용")
    val content: String
)