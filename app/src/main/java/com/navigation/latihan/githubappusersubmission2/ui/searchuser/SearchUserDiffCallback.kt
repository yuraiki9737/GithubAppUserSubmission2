package com.navigation.latihan.githubappusersubmission2.ui.searchuser

import androidx.recyclerview.widget.DiffUtil
import com.navigation.latihan.githubappusersubmission2.data.model.User

class SearchUserDiffCallback (private val oldUserList: ArrayList<User>, private val newUserList: ArrayList<User>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldUserList.size
    }
    override fun getNewListSize(): Int {
        return newUserList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUserList[oldItemPosition].id == newUserList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = oldUserList[oldItemPosition]
        val newEmployee = newUserList[newItemPosition]
        return oldEmployee.login == newEmployee.login && oldEmployee.id == newEmployee.id
    }
}