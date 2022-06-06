package com.kotlinapp.demoapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinapp.demoapp.dataClass.ImageApi
import com.kotlinapp.demoapp.network.Repository
import com.kotlinapp.demoapp.network.Retrofit
import kotlinx.coroutines.*

class HomeViewModel(val repository: Repository): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val homephoto = MutableLiveData<ImageApi>()
    var job: Job? = null
    var loading = MutableLiveData<Boolean>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getHomeImg(page:String) {
        val retrofitService = Retrofit.getInstance()
        val mainRepository = Repository(retrofitService)
        loading.value = true
        job = CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
             val response = mainRepository.getImg(page)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    homephoto.value =response.body()
                    loading.value = false
                    Log.d("response.body()","${response.body()}")
                } else {
                    Log.d("data", response.body().toString())

                }
            }

        }

    }






    private fun onError(message: String) {
        try {
            errorMessage.value = message
            loading.value = false
            return
        }catch (e:Exception){}
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}