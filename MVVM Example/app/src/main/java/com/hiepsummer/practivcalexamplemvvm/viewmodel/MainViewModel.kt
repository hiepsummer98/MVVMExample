package com.hiepsummer.practivcalexamplemvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hiepsummer.practivcalexamplemvvm.model.Video


class MainViewModel() : ViewModel() {
    val movieRepository = VideoRepository()
//    val allVideo: LiveData<List<Video>> get() = movieRepository.getMutableLiveData()
    fun getAllVideo(): MutableLiveData<List<Video>> {
        return movieRepository.getMutableLiveData()
    }
    override fun onCleared() {
        super.onCleared()
        movieRepository.complatetableJob.cancel()
    }

}