package com.example.velog.domain.exception

data class ModelNotFoundException(
    private val modelName: String,
    private val id: Long
) : RuntimeException("Model $modelName not found with given id: $id")