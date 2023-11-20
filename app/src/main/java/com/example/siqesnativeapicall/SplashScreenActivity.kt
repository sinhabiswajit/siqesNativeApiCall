package com.example.siqesnativeapicall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_splash_screen)

//        val splashScreenDrawable = findViewById<LottieAnimationView>(R.id.iv_splash_drawable)
//        Glide.with(this).asFile().load(R.raw.lotte_quote_animation).into(splashScreenDrawable)

        Handler().postDelayed({
            startMainActivity()
        }, 2000)
    }

    private fun startMainActivity() {
        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}