package com.navigation.latihan.githubappusersubmission2.ui.detail.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.navigation.latihan.githubappusersubmission2.databinding.ActivityDetailUserBinding
import com.navigation.latihan.githubappusersubmission2.ui.detail.adapterview.ViewPager
import com.navigation.latihan.githubappusersubmission2.ui.detail.modeldata.DetailUserViewModel
import com.navigation.latihan.githubappusersubmission2.ui.roomfavorite.UserFavoriteActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var modelView : DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val roomId = intent.getIntExtra(EXTRA_ROOM_ID,0)
        val profile = intent.getStringExtra(EXTRA_PROFILE)
        val roomLinkUrl = intent.getStringExtra(EXTRA_LINK_URL)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)


        binding.btnFavorite.setOnClickListener{
            val view = Intent(this@DetailUserActivity, UserFavoriteActivity::class.java)
            startActivity(view)
        }

        showLoading(true)
        modelView = ViewModelProvider(
            this).get(DetailUserViewModel::class.java)

        if (username != null) {
            modelView.setDetail(username)
        }
        modelView.getDetailUser().observe(this, {
            if (it != null) {
                binding.apply {
                   username1.text = it.login
                    name.text = it.name
                    company.text = it.company
                    location.text = it.location
                    repository.text = "${it.public_repos}"
                    followers.text = "${it.followers}"
                    following.text = "${it.following}"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(photo)
                    showLoading(false)
                }
            }
        })

        var roomCheckUser = false
        CoroutineScope(Dispatchers.IO).launch {
            val roomCount = modelView.roomCheck(roomId)
            withContext(Dispatchers.Main){
                if(roomCount!=null){
                    if(roomCount>0){
                        binding.toggleButton.isChecked=true
                        roomCheckUser = true
                    }else{
                        binding.toggleButton.isChecked = false
                        roomCheckUser = false
                    }

                }
            }
        }

        binding.toggleButton.setOnClickListener{
            roomCheckUser = !roomCheckUser
            if(roomCheckUser){
                modelView.roomInsertUser(username.toString(), roomLinkUrl.toString(),roomId,
                    profile.toString()
                )
            }else{
                modelView.delete(roomId)
            }
            binding.toggleButton.isChecked = roomCheckUser
        }



        val pager = ViewPager(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = pager
            tabs.setupWithViewPager(viewPager)
        }
    }



    private fun showLoading(state:Boolean){
        if (state){
            binding.barProgress.visibility = View.VISIBLE
        }else{
            binding.barProgress.visibility = View.GONE
        }

    }

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ROOM_ID = "extra_room_id"
        const val EXTRA_LINK_URL = "extra_link_url"
        const val EXTRA_PROFILE = "extra_profile"
    }

}