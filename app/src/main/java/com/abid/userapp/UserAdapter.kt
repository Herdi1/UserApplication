package com.abid.userapp

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abid.userapp.data.response.DataItem
import com.abid.userapp.databinding.ItemUserBinding
import com.bumptech.glide.Glide

class UserAdapter(private val activity: Activity): PagingDataAdapter<DataItem, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(private val binding: ItemUserBinding, private val activity: Activity): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem){
            binding.userName.text = String.format(activity.getString(R.string.full_name), user.firstName, user.lastName)
            binding.email.text = user.email
            Glide.with(binding.root.context)
                .load(user.avatar)
                .into(binding.profilePicture)

            itemView.setOnClickListener {
                val data = Intent()
                data.putExtra("username", String.format(activity.getString(R.string.full_name), user.firstName, user.lastName))
                activity.setResult(Activity.RESULT_OK, data)
                Log.d("adapter", data.toString())
                activity.finish()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}