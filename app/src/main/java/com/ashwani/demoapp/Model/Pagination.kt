package com.ashwani.demoapp.Model

data class Pagination(
    val currentItems: Int,
    val currentPage: Int,
    val links: List<Link>,
    val totalItems: Int,
    val totalPages: Int
)