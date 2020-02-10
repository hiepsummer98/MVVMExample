package com.hiepsummer.practivcalexamplemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiepsummer.practivcalexamplemvvm.model.Video
import com.hiepsummer.practivcalexamplemvvm.networking.ResApiService
import kotlinx.coroutines.*
import retrofit2.HttpException

class VideoRepository() {
    private var videos = mutableListOf<Video>()
    private var mutubleLiveData = MutableLiveData<List<Video>>()
    val complatetableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + complatetableJob)

    private val thisApiCorService by lazy {
        ResApiService.createCorService()
    }

    fun getMutableLiveData(): MutableLiveData<List<Video>> {
        coroutineScope.launch {
            val request = thisApiCorService.getVideo()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    val videoWrapper = response
                    if (videoWrapper != null && videoWrapper.video != null) {
                        videos = videoWrapper.video as MutableList<Video>
                        getMutableLiveData().value = videos
                    }
                } catch (e: HttpException) {
                    //Log exception here
                } catch (e: Throwable) {
                    //log error here
                }
            }
        }
        return mutubleLiveData
    }
}