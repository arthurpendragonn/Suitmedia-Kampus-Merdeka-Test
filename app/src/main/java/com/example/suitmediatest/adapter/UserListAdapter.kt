package com.example.suitmediatest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatest.data.UserResponseItem
import com.example.suitmediatest.databinding.UserItemBinding

class UserListAdapter(
    private val listener:OnItemClickListener
) : PagingDataAdapter<UserResponseItem, UserListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }

        holder.itemView.setOnClickListener{
            val name = (data?.first_name ?: "Selected") + " " + (data?.last_name ?: "User")
            listener.itemClick(name)
        }

    }

    class MyViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserResponseItem) {
            binding.tvUsername.text = "${data.first_name} ${data.last_name}"
            binding.tvEmail.text = data.email

            Glide.with(itemView.context)
                .load(data.avatar)
                .into(binding.imgAvatar)

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserResponseItem>() {
            override fun areItemsTheSame(oldItem: UserResponseItem, newItem: UserResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserResponseItem, newItem: UserResponseItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}