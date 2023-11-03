package com.example.app_01_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_01_project.databinding.ActivityMainBinding
import com.example.app_01_project.splash_screen_fragments.SplashFirstFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_layout,SplashFirstFragment())
            .commit()
    }
}