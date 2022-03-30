package com.navigation.latihan.githubappusersubmission2.ui.detail.modeldata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navigation.latihan.githubappusersubmission2.api.RetrofitClient
import com.navigation.latihan.githubappusersubmission2.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    val list_Following = MutableLiveData<ArrayList<User>>()


    fun setFollowingUser(username : String){
        RetrofitClient.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        list_Following.postValue(response.body())
                    }else{
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.e(TAG, "onFailure ${t.message.toString()}")
                }

            })
    }

    fun getFollowingUser(): LiveData<ArrayList<User>> {
        return list_Following
    }
    companion object{
        const val TAG = "FollowingViewModel"
    }
}