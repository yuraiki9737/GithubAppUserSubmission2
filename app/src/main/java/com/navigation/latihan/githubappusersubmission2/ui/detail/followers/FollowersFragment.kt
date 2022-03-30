package com.navigation.latihan.githubappusersubmission2.ui.detail.followers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.navigation.latihan.githubappusersubmission2.R
import com.navigation.latihan.githubappusersubmission2.ui.searchuser.SearchUserAdapter
import com.navigation.latihan.githubappusersubmission2.databinding.FragmentFollowBinding
import com.navigation.latihan.githubappusersubmission2.ui.detail.user.DetailUserActivity
import com.navigation.latihan.githubappusersubmission2.ui.detail.modeldata.FollowersViewModel

class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var bind : FragmentFollowBinding? = null
    private val bind_ get() = bind
    private lateinit var viewModel : FollowersViewModel
    private lateinit var adapterUser: SearchUserAdapter
    private lateinit var username : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         bind = FragmentFollowBinding.bind(view)
        val arg = arguments
        username = arg?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        adapterUser = SearchUserAdapter()




        bind_?.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapterUser
        }

        showLoading(true)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)

        viewModel.setFollowersUser(username)
        viewModel.getFollowersUser().observe(viewLifecycleOwner,{
            if (it != null){
                adapterUser.setList(it)
                showLoading(false)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        bind = null
    }

    private fun showLoading(loading: Boolean) {
        if (loading){
            bind_?.barProgress?.visibility = View.VISIBLE
        }else{
            bind_?.barProgress?.visibility = View.GONE
        }

    }

}