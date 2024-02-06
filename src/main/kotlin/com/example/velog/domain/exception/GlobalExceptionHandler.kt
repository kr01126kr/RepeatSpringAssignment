package com.example.velog.domain.exception

import com.example.velog.domain.exception.dto.ErrorResponseDto
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponseDto(e.message))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto(e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto("${e.bindingResult.fieldErrors.first().defaultMessage}"))
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleMethodBadCredentialsException(e: BadCredentialsException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponseDto("아이디 또는 비밀번호를 확인해주세요."))
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(e: DataIntegrityViolationException): ResponseEntity<ErrorResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CONFLICT) //중복된 값이 있을 때 사용
            .body(ErrorResponseDto(e.message))
    }

    @ExceptionHandler(AuthException::class)
    fun handleAuthException(e: AuthException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponseDto(e.message))
    }

    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddenException(e: ForbiddenException): ResponseEntity<ErrorResponseDto>{
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ErrorResponseDto(e.message))
    }
}