package com.medxplain.pref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefsManager {

    private val TAG = "PrefsManager"

    var preferences: SharedPreferences?=null
    var editor:SharedPreferences.Editor?=null
    private val GSON = Gson()


    var typeOfObject = object: TypeToken<Any>() {
    }.type!!



        constructor(context: Context) {
            preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)
            editor = preferences!!.edit()
        }




    companion object {
        var singleton: PrefsManager? = null
        fun with(context: Context) : PrefsManager {
            if (singleton == null) {
                singleton =  Builder(context).build()
            }
            return singleton!!
        }
    }





    fun save(key:String, value:Boolean) {
        editor!!.putBoolean(key, value).apply()
    }
    fun save(key:String, value:String) {
        editor!!.putString(key, value).apply()
    }
    fun save(key:String, value:Int) {
        editor!!.putInt(key, value).apply()
    }
    fun save(key:String, value:Float) {
        editor!!.putFloat(key, value).apply()
    }
    fun save(key:String, value:Long) {
        editor!!.putLong(key, value).apply()
    }

    fun save(key:String, value:Set<String>) {
        editor!!.putStringSet(key, value).apply()
    }
    // to save object in preference
    fun save(key:String, `object`:Any) {
        if (`object` == null)
        {
            throw IllegalArgumentException("object is null")
        }
        if (key.isNullOrEmpty())
        {
            throw IllegalArgumentException("key is empty or null")
        }
        editor!!.putString(key, GSON.toJson(`object`)).apply()
    }


    fun <T> save(key:String ,list :List<T>)  {
        if (list.isNullOrEmpty()) {
            throw  IllegalArgumentException("object is null")
        }

        if (key.isNullOrEmpty()) {
            throw  IllegalArgumentException("key is empty or null")
        }
        editor!!.putString(key, GSON.toJson(list)).apply()
    }



    fun <T> getObject(key: String ,a :Class<T>) : T?{
        val gson = preferences!!.getString(key, null)
        return if (gson == null) {
            null
        } else {
            try {
                GSON.fromJson(gson, a)
            } catch (e :Exception) {
                throw  IllegalArgumentException("Object storaged with key "
                        + key + " is instanceof other class")
            }
        }
    }


    fun getBoolean(key:String, defValue:Boolean):Boolean {
        return preferences!!.getBoolean(key, defValue)
    }
    fun getString(key:String, defValue:String):String {
        return preferences!!.getString(key, defValue)!!
    }
    fun getInt(key:String, defValue:Int):Int {
        return preferences!!.getInt(key, defValue)
    }
    fun getFloat(key:String, defValue:Float):Float {
        return preferences!!.getFloat(key, defValue)
    }
    fun getLong(key:String, defValue:Long):Long {
        return preferences!!.getLong(key, defValue)
    }
    fun getStringSet(key:String, defValue:Set<String>):Set<String> {
        return preferences!!.getStringSet(key, defValue)!!
    }

    fun getAll():Map<String, *> {
        return preferences!!.getAll()
    }
    fun remove(key:String) {
        editor!!.remove(key).apply()
    }
    fun removeAll() {
        editor!!.clear()
        editor!!.apply()
    }


    private class Builder(context:Context) {
        private val context:Context
        init{
            if (context == null)
            {
                throw IllegalArgumentException("Context must not be null.")
            }
            this.context = context.applicationContext
        }

        fun build(): PrefsManager {
            return PrefsManager(context)
        }
    }


}