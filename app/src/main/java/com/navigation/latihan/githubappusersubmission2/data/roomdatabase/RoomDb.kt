package com.navigation.latihan.githubappusersubmission2.data.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [RoomUser::class],
    version = 1
)
abstract class RoomDb : RoomDatabase() {
    abstract fun daoRoom() : RoomUserDao

    companion object{
        private  var roomIntances :  RoomDb? = null
        fun roomDb(state:Context) : RoomDb?{
            if(roomIntances==null){
                synchronized(RoomDb::class){
                    roomIntances = Room.databaseBuilder(state.applicationContext,RoomDb::class.java,"db_roomUser").build()
                }
            }
            return roomIntances
        }
    }
}