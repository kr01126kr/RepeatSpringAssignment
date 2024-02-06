package com.example.velog.domain.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import kotlin.jvm.Throws

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val objectMapper = ObjectMapper()

    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        response?.characterEncoding = "UTF-8"
        val errorMessage = "Access is allowed only for registered users."
        val errorResponse = objectMapper.writeValueAsString(mapOf("Error 401 - Unauthorized" to errorMessage))
        response?.writer?.write(errorResponse)
    }
}