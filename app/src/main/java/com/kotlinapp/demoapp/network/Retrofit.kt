package com.kotlinapp.demoapp.network

import com.kotlinapp.demoapp.dataClass.ImageApi
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface Retrofit {


    @GET("list")
    suspend fun homeData(@Query("page")page:String):Response<ImageApi>


    companion object{
        var URL = "https://picsum.photos/v2/"
        var retrofitService:Retrofit? = null

 fun getInstance():Retrofit{


     if (retrofitService == null){
         val retrofit = retrofit2.Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()
         retrofitService = retrofit.create(Retrofit::class.java)
     }
     return retrofitService!!
 }

     }
}