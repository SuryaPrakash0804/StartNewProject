package com.medxplain.network

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.RelativeLayout
import com.medxplain.R
import com.medxplain.utils.hide
import com.medxplain.utils.visible

import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception


object LoadingBox {


    var progressbar:Dialog?=null

    fun LoadingBoxShow(@ApplicationContext context:Context){



        try {

            if (isProgressbarVisible()){
                dismissProgressBar()
            }

            if (progressbar ==null && context!=null) {
                progressbar = Dialog(context)
            }
            // progressbar?.window?.attributes?.windowAnimations = R.style.DialogAnimation
            progressbar?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressbar?.setContentView(R.layout.loading_box)
            progressbar?.setCancelable(false)

            val relativeLayout = progressbar?.findViewById(R.id.rlProgress) as RelativeLayout

            relativeLayout.visible()
            progressbar?.show()

        }catch (e:Exception){}

    }

    fun isProgressbarVisible():Boolean{
        return if (progressbar ==null)
            false
        else progressbar?.isShowing!!
    }

    fun dismissProgressBar(){

        try {
            if (progressbar == null)
                return
            else if (progressbar != null) {
                progressbar?.hide()
                val relativeLayout = progressbar?.findViewById(R.id.rlProgress) as RelativeLayout
                relativeLayout.hide()
                progressbar = null

            }
        }
        catch (e:Exception){
            Log.e("progress Exception ","Exception ${e.message}")
        }

    }

}