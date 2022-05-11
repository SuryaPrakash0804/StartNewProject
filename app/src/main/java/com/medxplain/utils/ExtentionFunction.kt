package com.medxplain.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.medxplain.R
import com.medxplain.network.ApiResult
import com.medxplain.network.LoadingBox


fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}


fun View.snackbar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}


fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


fun View.visible()
{
    this.visibility=View.VISIBLE
}

fun View.hide(){
    this.visibility=View.GONE
}

fun Context.openKeyBoard(view: Activity)
{
    val imm =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val v = view.currentFocus
    if (v != null) imm.showSoftInput(v, 0)
}


fun Context.showLoading(){
    LoadingBox.LoadingBoxShow(this)
}

fun Context.hideLoading(){
    LoadingBox.dismissProgressBar()
}


fun View.handleApiError(
    failure: ApiResult.Error,
    context: Context

) {
    when {
        failure.isNetworkError -> snackbar(
            context.resources.getString(R.string.internet_error)
        )
        failure.errorCode == 401 -> {
          //  signOut(context)
        }
        failure.errorCode==402 -> {
         //   signOut(context)
        }
        failure.errorCode==100 -> {
            context.toast(failure.errorBody.toString())
        }
        else -> {
            val error = failure.errorBody.toString()
            context.toast(error)
          //  snackbar(error)
        }
    }
}



fun Context.openGoogleMap(lat:String,lang:String){
   // val uri: String = java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", lat, lang)
    val uri = "http://maps.google.com/maps?q=loc:$lat,$lang (Apna Kangra)"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    startActivity(intent)
}




fun Context.shareWithSocial(image:String, content:String){

    var title = "Title to share" //Title you wants to share
//
//    val shareIntent =  Intent()
//    shareIntent.action = Intent.ACTION_SEND
//
//    shareIntent.type = "image/*"
//    shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//    shareIntent.putExtra(Intent.EXTRA_TEXT, content)
//    shareIntent.putExtra(Intent.EXTRA_STREAM,image)
//    startActivity(Intent.createChooser(shareIntent, "Select App to Share Text and Image"))



    val imageUri = Uri.parse(
        image
    )
    val shareIntent = Intent()
    shareIntent.action = Intent.ACTION_SEND
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, content)
    shareIntent.putExtra(Intent.EXTRA_TEXT, image)
    //shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
    shareIntent.type = "text/plain"
    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    startActivity(Intent.createChooser(shareIntent, "Select App to Share Text and Image"))
}



