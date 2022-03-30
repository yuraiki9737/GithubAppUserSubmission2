package com.navigation.latihan.githubappusersubmission2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.navigation.latihan.githubappusersubmission2.databinding.ActivityMainBinding
import com.navigation.latihan.githubappusersubmission2.helper.SettingPreferences
import com.navigation.latihan.githubappusersubmission2.ui.roomfavorite.UserFavoriteActivity
import com.navigation.latihan.githubappusersubmission2.ui.searchuser.SearchUserActivity
import com.navigation.latihan.githubappusersubmission2.ui.setting.SettingActivity
import com.navigation.latihan.githubappusersubmission2.ui.setting.SettingActivity.Companion.DARK_MODE


class MainActivity : AppCompatActivity() {

    private val pref by lazy { SettingPreferences(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when(pref.getBoolean(DARK_MODE)){
            true->{

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false ->{

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }
        binding.btnSearch.setOnClickListener {
            val view = Intent(this@MainActivity, SearchUserActivity::class.java)
            startActivity(view)

        }

        binding.btnFavorite.setOnClickListener{
            val view = Intent(this@MainActivity, UserFavoriteActivity::class.java)
            startActivity(view)
        }

        binding.btnSetting.setOnClickListener {
            val view = Intent(this@MainActivity, SettingActivity::class.java)
            startActivity(view)

        }

    }
}