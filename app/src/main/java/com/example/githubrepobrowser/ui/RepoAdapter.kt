package com.example.githubrepobrowser.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepobrowser.R
import com.example.githubrepobrowser.data.Repo
import java.text.SimpleDateFormat
import java.util.*

class RepoAdapter : ListAdapter<Repo, RepoAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvStars: TextView = view.findViewById(R.id.tvStars)
        val tvCreated: TextView = view.findViewById(R.id.tvCreated)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)

        holder.tvName.text = item.name
        holder.tvStars.text = "⭐ ${item.stargazers_count}"

        val formattedDate = runCatching {
            val sdfIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val date = sdfIn.parse(item.created_at)
            val sdfOut = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdfOut.format(date!!)
        }.getOrDefault("—")

        holder.tvCreated.text = "Creato il $formattedDate"

        //  Si apre il repo cliccando sulla card:
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.html_url))
            holder.itemView.context.startActivity(intent)
        }
    }
}
