package com.example.quotesapp.domin.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Localdb {
    @Insert
    suspend fun interfav(localtable: localtable)

    @Query("SELECT * FROM favoritetb ORDER BY ID DESC")
     fun getfavorittable():Flow<List<localtable>>

}