package com.navigation.latihan.githubappusersubmission2.ui.roomfavorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.navigation.latihan.githubappusersubmission2.data.roomdatabase.RoomUser
import com.navigation.latihan.githubappusersubmission2.data.model.User
import com.navigation.latihan.githubappusersubmission2.databinding.ActivityUserFavoriteBinding
import com.navigation.latihan.githubappusersubmission2.ui.detail.user.DetailUserActivity
import com.navigation.latihan.githubappusersubmission2.ui.roomfavorite.model.UserFavoriteRoomModel
import com.navigation.latihan.githubappusersubmission2.ui.searchuser.SearchUserAdapter

class UserFavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUserFavoriteBinding
    private lateinit var modelView : UserFavoriteRoomModel
    private lateinit var adapter : SearchUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SearchUserAdapter()
        modelView = ViewModelProvider(
            this).get((UserFavoriteRoomModel::class.java))
        adapter.setOnItemClickCallback(object : SearchUserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: User) {
                Intent(this@UserFavoriteActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                    it.putExtra(DetailUserActivity.EXTRA_ROOM_ID, user.id )
                    it.putExtra(DetailUserActivity.EXTRA_PROFILE, user.avatar_url)
                    it.putExtra(DetailUserActivity.EXTRA_LINK_URL, user.html_url)
                    startActivity(it)
                }
            }

        })
        binding.apply {
            rvRoomFavorite.setHasFixedSize(true)
            rvRoomFavorite.layoutManager = LinearLayoutManager(this@UserFavoriteActivity)
            rvRoomFavorite.adapter = adapter
        }

        modelView.getRoomFavoriteUser()?.observe(this) {
            if (it != null) {
                val list = mapList(it)
                adapter.setList(list)
            }
        }

    }

    private fun mapList(it: List<RoomUser>):ArrayList<User> {

        val userListRoom = ArrayList<User>()
        for (user in it){
            val roomMap = User(
                user.id,
                user.login,
                user.avatar_url,
                user.html_url
            )
            userListRoom.add(roomMap)
        }
        return userListRoom
    }
}