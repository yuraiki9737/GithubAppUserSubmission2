package com.navigation.latihan.githubappusersubmission2.ui.roomfavorite.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.navigation.latihan.githubappusersubmission2.data.roomdatabase.RoomDb
import com.navigation.latihan.githubappusersubmission2.data.roomdatabase.RoomUser
import com.navigation.latihan.githubappusersubmission2.data.roomdatabase.RoomUserDao

class UserFavoriteRoomModel (application: Application) : AndroidViewModel(application) {

    private var daoRoomUser : RoomUserDao?
    private var dbRoomUser : RoomDb?

    init {
        dbRoomUser = RoomDb.roomDb(application)
        daoRoomUser = dbRoomUser?.daoRoom()
    }

    fun getRoomFavoriteUser() : LiveData<List<RoomUser>>? {
        return daoRoomUser?.getRoomUser()
    }


}