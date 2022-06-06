package com.kotlinapp.demoapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlinapp.demoapp.dataClass.ImageApi
import com.kotlinapp.demoapp.dataClass.ImageApiItem

@Dao
interface Dao {

    //Home Main category Query
    @Query("SELECT * FROM image")
    fun getImage(): LiveData<ImageApiItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(get : ImageApiItem)

    @Query("DELETE FROM image")
    fun deleteAll()
}