package com.example.quotesapp.domin.localdb

class favrepository(var localdb: Localdb) {


     fun getfavdata()=localdb.getfavorittable()

    suspend fun insertdata(localtable: localtable){
        localdb.interfav(localtable)
    }
}