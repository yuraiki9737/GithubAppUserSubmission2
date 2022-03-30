package com.navigation.latihan.githubappusersubmission2.ui.detail.modeldata

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.navigation.latihan.githubappusersubmission2.api.RetrofitClient
import com.navigation.latihan.githubappusersubmission2.data.roomdatabase.RoomDb
import com.navigation.latihan.githubappusersubmission2.data.roomdatabase.RoomUser
import com.navigation.latihan.githubappusersubmission2.data.roomdatabase.RoomUserDao
import com.navigation.latihan.githubappusersubmission2.data.model.DetailResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application){


    val detailUser = MutableLiveData<DetailResponse>()

    private var daoRoomUser: RoomUserDao?
    private var dbRoomUser : RoomDb?

    init {
        dbRoomUser = RoomDb.roomDb(application)
        daoRoomUser = dbRoomUser?.daoRoom()
    }

    fun setDetail(username : String){
        RetrofitClient.apiInstance
            .getDetailUser(username)
            .enqueue(object : Callback<DetailResponse> {
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    if(response.isSuccessful){
                        detailUser.postValue(response.body())
                    } else{
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")

                }

            })
    }
    fun getDetailUser() : LiveData<DetailResponse> {
        return detailUser
    }

    fun roomInsertUser(username: String, urlLink: String, id: Int,profile: String){
        CoroutineScope(Dispatchers.IO).launch {
            var roomUser = RoomUser(username,urlLink,id,profile)
            daoRoomUser?.insertRoomUser(roomUser)
        }
    }

    suspend fun roomCheck(id: Int) = daoRoomUser?.roomCheck(id)

    fun delete(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            daoRoomUser?.roomDeleteData(id)
        }
    }
    companion object{
        const val TAG = "DetailViewModel"
    }

}