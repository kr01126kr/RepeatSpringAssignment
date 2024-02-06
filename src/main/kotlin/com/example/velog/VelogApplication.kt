package com.example.velog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing //AuditingEntityListener 기능 사용하기 위해서 추가
class VelogApplication

fun main(args: Array<String>) {
    runApplication<VelogApplication>(*args)
}
