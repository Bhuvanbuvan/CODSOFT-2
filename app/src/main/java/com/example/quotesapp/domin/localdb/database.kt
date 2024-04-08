package com.example.quotesapp.domin.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [localtable::class], version = 1)
abstract class database :RoomDatabase(){
    abstract fun localdb():Localdb

    companion object{
        @Volatile
        var INSTENCE:database?=null;

        fun getInstence(context: Context):database{
            synchronized(this){
                var instence= INSTENCE
                if (instence==null){
                    instence= Room.databaseBuilder(
                        context.applicationContext,
                        database::class.java,
                        "favoritdb"
                    ).build()
                }
                return instence
            }
        }
    }
}