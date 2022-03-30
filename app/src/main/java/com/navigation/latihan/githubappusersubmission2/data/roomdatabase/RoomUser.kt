package com.navigation.latihan.githubappusersubmission2.data.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "room_user")

data class RoomUser(

    val login: String,
    val html_url: String,


    @PrimaryKey
    val id: Int,
    val avatar_url: String
        )