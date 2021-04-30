package com.app.hepsiburada.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.hepsiburada.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_activity_home)
    }
}