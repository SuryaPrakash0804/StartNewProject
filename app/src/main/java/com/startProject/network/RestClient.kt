package com.startProject.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.startProject.utils.Constants.BASE_URL


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RestClient {


    companion object{


        var api : API_Url?=null
        var builder:Retrofit.Builder?=null


        fun get(): API_Url {


            if (builder == null) {
                builder =  Retrofit.Builder()
                builder!!.baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                builder!!.client(getClient())
                api = builder!!.build().create(API_Url::class.java)
            }
            return api!!
        }

        private fun getClient(): OkHttpClient {

            val logging =  HttpLoggingInterceptor()

            logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
            return  OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS).build()
        }


         fun getRetrofitBuilder():Retrofit{

            if (builder == null) {
                builder =  Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                builder!!.build()
            }
            return builder!!.build()
        }



        private fun gson(): Gson? {
            return  GsonBuilder().setLenient().create()
        }





    }


}