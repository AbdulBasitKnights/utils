package com.example.xillicommon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xillicommons.tools.XilliTools

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        XilliTools.showToast("Something went wrong.Try again!",this@MainActivity)
    }
}