package com.example.quotesapp.domin.localdb

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class favViewModel(var repo: favrepository):ViewModel() {

    fun getfavdata()=repo.getfavdata()


    fun insertdata(localtable: localtable){
        viewModelScope.launch {
            repo.insertdata(localtable)
        }
    }
}