package com.navigation.latihan.githubappusersubmission2.ui.detail.following

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.navigation.latihan.githubappusersubmission2.R
import com.navigation.latihan.githubappusersubmission2.ui.detail.user.DetailUserActivity
import com.navigation.latihan.githubappusersubmission2.ui.searchuser.SearchUserAdapter
import com.navigation.latihan.githubappusersubmission2.databinding.FragmentFollowBinding
import com.navigation.latihan.githubappusersubmission2.ui.detail.modeldata.FollowingViewModel

class FollowingFragment : Fragment(R.layout.fragment_follow) {

    private var _bind : FragmentFollowBinding? = null
    private val bind get() = _bind
    private lateinit var viewModel : FollowingViewModel
    private lateinit var followingUser: SearchUserAdapter
    private lateinit var username : String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _bind = FragmentFollowBinding.bind(view)

        followingUser= SearchUserAdapter()

        bind?.apply {
            rvUser.layoutManager = LinearLayoutManager(requireContext())
            rvUser.adapter = followingUser
            rvUser.setHasFixedSize(true)
        }

        showLoading(true)
        viewModel = ViewModelProvider(this).get(
            FollowingViewModel::class.java
        )

        viewModel.setFollowingUser(username)
        viewModel.getFollowingUser().observe(viewLifecycleOwner) {
            if (it != null) {
                showLoading(false)
                followingUser.setList(it)

            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            bind?.barProgress?.visibility = View.VISIBLE
        } else {
            bind?.barProgress?.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}