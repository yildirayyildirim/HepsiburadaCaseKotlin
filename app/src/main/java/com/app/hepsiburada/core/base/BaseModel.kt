package com.app.hepsiburada.core.base

data class BaseModel<T>(
    val resultCount: Int,
    val results: List<T>,
)