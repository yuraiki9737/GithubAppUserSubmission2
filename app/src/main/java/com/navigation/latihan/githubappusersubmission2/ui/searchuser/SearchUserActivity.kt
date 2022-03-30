package com.navigation.latihan.githubappusersubmission2.ui.searchuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.navigation.latihan.githubappusersubmission2.ui.detail.user.DetailUserActivity
import com.navigation.latihan.githubappusersubmission2.data.model.User
import com.navigation.latihan.githubappusersubmission2.databinding.ActivitySearchUserBinding

class SearchUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchUserBinding
    private lateinit var viewModel: SearchUserViewModel
    private lateinit var userAdapter: SearchUserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userAdapter = SearchUserAdapter()
        userAdapter.setOnItemClickCallback(object : SearchUserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: User) {
                Intent(this@SearchUserActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                    it.putExtra(DetailUserActivity.EXTRA_ROOM_ID, user.id )
                    it.putExtra(DetailUserActivity.EXTRA_PROFILE, user.avatar_url)
                    it.putExtra(DetailUserActivity.EXTRA_LINK_URL, user.html_url)
                    startActivity(it)
                }
            }
        })

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get((SearchUserViewModel::class.java))

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@SearchUserActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = userAdapter


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null && query.isNotEmpty()) {
                        searchUser()

                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }

        viewModel.getSearchUser().observe(this, {
            if (it != null) {
                userAdapter.setList(it)
                showLoading(false)
                binding.rvUser.visibility =View.VISIBLE
            }
        })
    }

    private fun searchUser(){
        binding.apply {
            val query = searchView.query.toString()
            if (query.isEmpty()) return
            binding.rvUser.visibility = View.GONE
            showLoading(true)
            viewModel.setSearchUser(query)

        }
    }



    private fun showLoading(state: Boolean) {
        if (state) {
            binding.barProgress.visibility = View.VISIBLE
        } else {
            binding.barProgress.visibility = View.GONE
        }

    }



}