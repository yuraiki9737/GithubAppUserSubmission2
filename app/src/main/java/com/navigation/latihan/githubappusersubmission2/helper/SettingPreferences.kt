package com.navigation.latihan.githubappusersubmission2.helper


import android.content.Context
import android.content.SharedPreferences

class SettingPreferences (prefContext: Context){

    private val name = "GithubAppUserSubmission2"
    private var pref : SharedPreferences = prefContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    val prefEdit: SharedPreferences.Editor = pref.edit()

    fun put(key: String, value : Boolean){
        prefEdit.putBoolean(key,value)
            .apply()
    }

    fun getBoolean(key : String):Boolean{
        return pref.getBoolean(key, false)
    }


}
