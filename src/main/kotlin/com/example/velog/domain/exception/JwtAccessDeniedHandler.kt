package com.example.velog.domain.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException
import kotlin.jvm.Throws

@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {
    private val objectMapper = ObjectMapper()

    @Throws(IOException::class)
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {

        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        response?.status = HttpServletResponse.SC_FORBIDDEN
        response?.characterEncoding = "UTF-8"
        val errorMessage = "You don't have permission to access."
        val errorResponse = objectMapper.writeValueAsString(mapOf("Error 403 - Forbidden" to errorMessage))
        response?.writer?.write(errorResponse)
    }
}