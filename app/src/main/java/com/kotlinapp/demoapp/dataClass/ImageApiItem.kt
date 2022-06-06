package com.kotlinapp.demoapp.dataClass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "image")
data class ImageApiItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idd") val idd: Int = 1,
    @ColumnInfo(name = "author") val author: String?=null,
    @ColumnInfo(name = "download_url")val download_url: String?=null,
    val height: Int?=null,
    val id: String?=null,
    val url: String?=null,
    val width: Int?=null
)