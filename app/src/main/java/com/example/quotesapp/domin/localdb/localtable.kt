package com.example.quotesapp.domin.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritetb")
data class localtable(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
val text:String,
val author:String

)
