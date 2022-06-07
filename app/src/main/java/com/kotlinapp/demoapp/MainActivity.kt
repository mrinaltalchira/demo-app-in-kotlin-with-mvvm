package com.kotlinapp.demoapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kotlinapp.demoapp.room.AppDatabase
import com.kotlinapp.demoapp.adapter.AdapterImg
import com.kotlinapp.demoapp.dataClass.ImageApi
import com.kotlinapp.demoapp.databinding.ActivityMainBinding
import com.kotlinapp.demoapp.network.Repository
import com.kotlinapp.demoapp.network.Retrofit

 /*                                               Please read this


                                                  what i've done !

         i've used MVVM architecture while building this application , Used Retrofit for Api Response .
         added shimmer loading effect while loading the data ,pending network error issue.
         added Room Databse .
         while using roomDB in this what i have done is to save data in roomDB and update/replace data when network
         is available and recive updated data ..

                                                   where im stucked

        while geting data from roomDB im facing a issue which is DataClass mismatch problem , problem is !
        adapter function need ImageApi (DataClass) but RoomDB is sending ImageApiItem (DataClass) which is child
        class of ImageApi. and rest of things are working totally fine.


        **** I do believe in to upgrade my skills day by day i do also have best and clean MVVM architecture patern
         which i haven't used in this due to lack of time

         there is a lots of things in it which i've to improve like
         memory management
         space management
         code reusablity
         handle minor things

         *****


*/



class MainActivity : AppCompatActivity() {
lateinit var appDatabaseobj:AppDatabase
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    lateinit var rView: RecyclerView

    lateinit var list:ImageApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabaseobj = AppDatabase.getAppDBInstance(this)
        val retrofitService = Retrofit.getInstance()
        val repository = Repository(retrofitService)
        viewModel = ViewModelProvider(this, VMFactory(repository)).get(
            HomeViewModel::class.java
        )

        rView = findViewById(R.id.rv_mainCategory)

        viewModel.homephoto.observe(this, Observer {
            if (it != null) {


                appDatabaseobj.getAppDao().deleteAll()
                appDatabaseobj.getAppDao().insertAll(it[0])

                val mainAdapter = AdapterImg()
                mainAdapter.setImg(it, this@MainActivity)
                Log.d("response.body()", it.toString())
                rView.adapter = mainAdapter

            }
        })

        viewModel.errorMessage.observe(this, Observer {

            binding.loadingShimmer.shimmerViewContainer.
            visibility = View.VISIBLE
        })
        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.loadingShimmer.shimmerViewContainer.visibility = View.VISIBLE
            } else {
                binding.loadingShimmer.shimmerViewContainer.visibility = View.GONE
                }
        })
        viewModel.getHomeImg("33")



    }

    fun getData(num: String) {

    /*    val listrd = appDatabaseobj.getAppDao().getImage().observe(this, Observer {
            val mainAdapter = AdapterImg()
        if (it != null){
            mainAdapter.setImg(  it , this@MainActivity)
        }
//            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()

        })*/


    }


}