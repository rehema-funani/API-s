package com.example.postapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.postsapp.CommentsActivity
import com.akirachix.postsapp.Posts
import com.akirachix.postsapp.databinding.PostListItemBinding

class PostsAdapter(var postsList: List<Posts>, val context: Context): RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostsViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = postsList[position]
        holder.binding.apply {
            tvBody.text = post.body
            tvTitle.text = post.title
            clPost.setOnClickListener{
                val intent = Intent(context, CommentsActivity::class.java)
                intent.putExtra("POST_ID", post.id)
                context.startActivity(intent)
            }
        }
    }
}

class PostsViewHolder(val binding: PostListItemBinding) :
    RecyclerView.ViewHolder(binding.root){

}