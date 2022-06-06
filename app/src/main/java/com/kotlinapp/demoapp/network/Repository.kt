package com.kotlinapp.demoapp.network

class Repository constructor(private val retrofitService: Retrofit) {
    //Get img
    suspend fun getImg(page: String) =
        retrofitService.homeData(page)
}