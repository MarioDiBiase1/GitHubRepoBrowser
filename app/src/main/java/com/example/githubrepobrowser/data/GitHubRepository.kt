package com.example.githubrepobrowser.data

import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val service: GitHubService
) {
    suspend fun getRepos(user: String): List<Repo> =
        service.listRepos(user)
}
