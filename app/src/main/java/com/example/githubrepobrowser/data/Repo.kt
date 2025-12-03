package com.example.githubrepobrowser.data

data class Repo(
    val id: Long,
    val name: String,
    val html_url: String,
    val stargazers_count: Int,
    val created_at: String,
    val owner: Owner
)

data class Owner(
    val login: String,
    val avatar_url: String
)
