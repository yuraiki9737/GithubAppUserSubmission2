package com.navigation.latihan.githubappusersubmission2.data.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RoomUserDao {

    @Query("DELETE FROM room_user WHERE room_user.id = :id")
    suspend fun roomDeleteData(id:Int):Int

    @Insert
    suspend fun insertRoomUser(roomUser : RoomUser)

    @Query("SELECT count(*) FROM room_user WHERE room_user.id = :id")
    suspend fun roomCheck(id :Int):Int

    @Query("SELECT * FROM room_user")
    fun getRoomUser(): LiveData<List<RoomUser>>


}