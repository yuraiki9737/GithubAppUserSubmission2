package com.navigation.latihan.githubappusersubmission2.ui.setting

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.navigation.latihan.githubappusersubmission2.databinding.ActivitySettingBinding
import com.navigation.latihan.githubappusersubmission2.helper.SettingPreferences


class SettingActivity : AppCompatActivity() {

    private val pref by lazy { SettingPreferences(this) }
    private lateinit var binding: ActivitySettingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.themeMode.isChecked = pref.getBoolean(DARK_MODE)
        binding.themeMode.setOnCheckedChangeListener{ _: CompoundButton?, isChecked ->
            when(isChecked){
                true->{
                    pref.put(DARK_MODE,true)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                false ->{
                    pref.put(DARK_MODE,false)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }

            }

        }

        binding.btnFrame.setOnClickListener {
            binding.cardView.visibility = View.VISIBLE
        }

        binding.btnClose.setOnClickListener {
            binding.cardView.visibility = View.GONE
        }
    }

    companion object{
        const val DARK_MODE = "dark_mode"
    }

}