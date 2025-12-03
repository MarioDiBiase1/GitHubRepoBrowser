package com.example.githubrepobrowser.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubrepobrowser.data.GitHubRepository
import com.example.githubrepobrowser.data.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _repos = MutableLiveData<List<Repo>>(emptyList())
    val repos: LiveData<List<Repo>> = _repos

    fun loadRepos(user: String) {
        viewModelScope.launch {
            val list = try {
                repository.getRepos(user)
            } catch (e: Exception) {
                emptyList()
            }
            _repos.value = list
        }
    }
}
