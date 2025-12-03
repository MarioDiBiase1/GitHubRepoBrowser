package com.example.githubrepobrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepobrowser.ui.RepoAdapter
import com.example.githubrepobrowser.vm.GitHubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: GitHubViewModel by viewModels()
    private lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputUser = findViewById<EditText>(R.id.inputUser)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = RepoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.repos.observe(this) { repos ->
            adapter.submitList(repos)
        }

        btnSearch.setOnClickListener {
            val user = inputUser.text.toString().trim()
            if (user.isNotEmpty()) {
                viewModel.loadRepos(user)
            }
        }
    }
}
