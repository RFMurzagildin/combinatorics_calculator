package com.app.calculator.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.calculator.R
import com.app.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.run {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MenuFragment(), "ToMenuFragmentFromMainActivity")
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}