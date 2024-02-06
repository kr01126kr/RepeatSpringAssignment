package com.example.velog.infra.auditing

import com.example.velog.domain.user.model.CustomUser
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
class AuditorConfig : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication == null || !authentication.isAuthenticated || authentication.principal is String && authentication.principal == "anonymousUser") {
            return Optional.of("anonymous")
        }


        val user: CustomUser = authentication.principal as CustomUser

        return Optional.of(user.name) //현재 토큰의 사용자 이름을 가져온다
    }
}