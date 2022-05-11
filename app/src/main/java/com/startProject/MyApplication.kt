package com.startProject

import android.app.Application
import android.content.Context
import com.startProject.utils.LocaleHelper
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext




    }



    override fun attachBaseContext(base: Context?) {
        if (LocaleHelper.getLanguage(base!!).isNullOrEmpty())
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
        else super.attachBaseContext(LocaleHelper.onAttach(base, LocaleHelper.getLanguage(base)!!))
    }


    companion object {
        var appContext: Context?=null

    }




}

