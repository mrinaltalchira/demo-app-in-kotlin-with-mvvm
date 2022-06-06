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

//        val listrd = appDatabaseobj.getAppDao().getImage().observe(this, Observer {
//            val mainAdapter = AdapterImg()
//        if (it != null){
////            mainAdapter.setImg(  it , this@MainActivity)
//        }
////            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
//
//        })


    }


}