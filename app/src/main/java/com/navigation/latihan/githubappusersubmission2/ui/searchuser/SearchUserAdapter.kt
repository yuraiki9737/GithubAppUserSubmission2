package com.navigation.latihan.githubappusersubmission2.ui.searchuser

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.navigation.latihan.githubappusersubmission2.data.model.User
import com.navigation.latihan.githubappusersubmission2.databinding.ItemUserGithubBinding

class SearchUserAdapter : RecyclerView.Adapter<SearchUserAdapter.UserViewHolder>() {

    private val listUser = ArrayList<User>()
    private var onItemClick : OnItemClickCallback? = null

   fun setOnItemClickCallback(OnItemClick : OnItemClickCallback){
       this.onItemClick = OnItemClick
   }


    fun setList(user: ArrayList<User>) {
        val diffCallback = SearchUserDiffCallback(this.listUser,user)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listUser.clear()
        listUser.addAll(user)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class UserViewHolder( val binding: ItemUserGithubBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun binding(user: User) {
            binding.root.setOnClickListener {
                onItemClick?.onItemClicked(user)
            }


            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(photo)
                id.text = "ID : ${user.id}"
                username.text = user.login
                url.text = user.html_url
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding(listUser[position])
    }



    override fun getItemCount(): Int = listUser.size
    interface OnItemClickCallback {
        fun onItemClicked(user: User)
    }

}

