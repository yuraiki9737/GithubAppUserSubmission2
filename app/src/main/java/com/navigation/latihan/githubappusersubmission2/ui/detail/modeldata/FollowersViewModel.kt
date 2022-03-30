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

class FollowersViewModel : ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<User>>()

    fun setFollowersUser(username : String){
        RetrofitClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        listFollowers.postValue(response.body())
                    } else{
                        Log.e(TAG1, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.e(TAG1, "onFailure ${t.message.toString()}")
                }

            })
    }

    fun getFollowersUser(): LiveData<ArrayList<User>> {
        return listFollowers
    }

    companion object{
        const val TAG1 = "FollowersViewModel"
    }

}