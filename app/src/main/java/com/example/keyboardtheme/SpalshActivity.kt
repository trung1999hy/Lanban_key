package com.example.keyboardtheme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.keyboardtheme.gt.keybord.KeyBoardActivity

class SpalshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, KeyBoardActivity::class.java))
            finish()
        },2000)
    }
}