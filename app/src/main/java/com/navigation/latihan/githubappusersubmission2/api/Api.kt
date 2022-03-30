package com.navigation.latihan.githubappusersubmission2.api

import com.navigation.latihan.githubappusersubmission2.data.model.DetailResponse
import com.navigation.latihan.githubappusersubmission2.data.model.User
import com.navigation.latihan.githubappusersubmission2.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search/users")
     fun getSearch(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
     fun getDetailUser (
        @Path("username") username : String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers (
        @Path("username") username : String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    fun getFollowing (
        @Path("username") username : String
    ): Call<ArrayList<User>>


}