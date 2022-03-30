package com.navigation.latihan.githubappusersubmission2.ui.searchuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navigation.latihan.githubappusersubmission2.api.RetrofitClient
import com.navigation.latihan.githubappusersubmission2.data.model.User
import com.navigation.latihan.githubappusersubmission2.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserViewModel : ViewModel(){

    val listUser = MutableLiveData<ArrayList<User>>()


    fun setSearchUser(query: String) {
        RetrofitClient.apiInstance
            .getSearch(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUser.postValue(response.body()?.items)
                    }else{
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }

                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")

                }
            })
    }
    fun getSearchUser(): LiveData<ArrayList<User>> {
        return listUser
    }

    companion object{
        const val TAG = "SearchViewModel"
    }

}
