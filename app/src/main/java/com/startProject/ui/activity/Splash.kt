package com.startProject.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi

import com.startProject.databinding.ActivitySplashBinding


class Splash : BaseActivity(){

    lateinit var binding: ActivitySplashBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)




       // binding.tvVersion.text="Version ${BuildConfig.VERSION_NAME}"
    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()

        startSplash()
    }














    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startSplash() {
        Thread(Runnable {

            val homeintent=Intent(this,Home_Activity::class.java)

            if (intent.extras!=null && intent.hasExtra("type")){
                homeintent.putExtra("type",intent.getStringExtra("type"))
                homeintent.putExtra("title",intent.getStringExtra("title"))
            }
                Thread.sleep(1000)
                startActivity(homeintent)
                finish()

        }).start()


    }






}