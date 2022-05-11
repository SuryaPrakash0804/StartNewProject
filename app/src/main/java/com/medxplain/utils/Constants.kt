package com.medxplain.utils

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.medxplain.R

import com.medxplain.pref.PrefsManager
import java.text.SimpleDateFormat
import java.util.*


object Constants {


    const val BASE_URL="http://poc.apnakangra.org/wp-json/"


    const val USER_DATA = "user_data"



//    fun getToken(context: Context): String {
//
//        return if (PrefsManager.with(context)
//                .getObject(USER_DATA, Auth_Bean::class.java) != null &&
//            !PrefsManager.with(context)
//                .getObject(USER_DATA, Auth_Bean::class.java)?.token.isNullOrEmpty()
//        ) {
//            "Bearer " + PrefsManager.with(context)
//                .getObject(USER_DATA, Auth_Bean::class.java)?.token
//        } else ""
//    }


//    fun getTokenwithoutBearer(context: Context): String {
//
//        return if (PrefsManager.with(context)
//                .getObject(USER_DATA, Auth_Bean::class.java) != null &&
//            !PrefsManager.with(context)
//                .getObject(USER_DATA, Auth_Bean::class.java)?.token.isNullOrEmpty()
//        ) {
//            PrefsManager.with(context)
//                .getObject(USER_DATA, Auth_Bean::class.java)?.token!!
//        } else ""
//    }












    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }


    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }



    fun isNetworkAvailableII(context: Context): Boolean {
        var isnetwork=false

        try {

            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw = connectivityManager.activeNetwork
                val actNw = connectivityManager.getNetworkCapabilities(nw)
                isnetwork= when {
                    actNw?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)!! -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    //for other device how are able to connect with Ethernet
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    //for check internet over Bluetooth
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } else {
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                isnetwork= nwInfo.isConnected
            }
//            return if (isnetwork)
//                true
//            else{
//                context.toast(context.resources.getString(R.string.internet_error))
//                false
//            }
        }
        catch (e:Exception){
            context.toast(context.resources.getString(R.string.internet_error))
            return false
        }
        return if (isnetwork)
            true
        else{
            context.toast(context.resources.getString(R.string.internet_error))
            false
        }

    }




    fun distance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Int {
//         var locationA=Location("point A")
//         locationA.latitude=lat1
//         locationA.longitude=lon1
//
//         var locationB=Location("point B")
//         locationB.latitude=lat2
//         locationB.longitude=lon2
//
//        var distancefloat=  locationA.distanceTo(locationB)
        val distance = FloatArray(2)
        Location.distanceBetween(lat1, lon1, lat2, lon2, distance)
        return distance[0].toInt()

    }



//    fun showExitDialog(context: Activity) {
//
//        var dialog_exit = Dialog(context)
//        dialog_exit.window?.attributes?.windowAnimations = R.style.DialogAnimation
//        dialog_exit.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog_exit.setContentView(R.layout.app_exite_dialog)
//        dialog_exit.setCancelable(false)
//
//        val yes = dialog_exit.findViewById(R.id.tv_yes) as TextView
//        val no = dialog_exit.findViewById(R.id.tv_no) as TextView
//
//
//        no.setOnClickListener { dialog_exit.dismiss() }
//
//        yes.setOnClickListener {
//            dialog_exit.dismiss()
//            context.finishAffinity()
//        }
//
//        dialog_exit.show()
//
//    }
























}