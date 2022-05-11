package com.medxplain.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.medxplain.R
import com.medxplain.utils.LocaleHelper



abstract class BaseActivity:AppCompatActivity() {


    override fun onCreate(@Nullable savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))

    }

    override fun onResume() {

        super.onResume()

    }

    override fun onRestart() {
        LocaleHelper.onAttach(applicationContext)
        super.onRestart()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun startActivity(intent: Intent) {

        super.startActivity(intent)
        overridePendingTransition(R.anim.parallax_right_in, R.anim.parallax_left_out)
    }






    override fun startActivityForResult(intent: Intent?, requestCode:Int) {
        super.startActivityForResult(intent, requestCode)
        overridePendingTransition(R.anim.parallax_right_in, R.anim.parallax_left_out)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.parallax_left_in, R.anim.parallax_right_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.parallax_left_in, R.anim.parallax_right_out)
    }
    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onStart() {
        super.onStart()


    }










}