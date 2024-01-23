package com.example.xillicommon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xillicommons.tools.VisionTools

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VisionTools.showToast("Something went wrong.Try again!",this@MainActivity)
    }
}