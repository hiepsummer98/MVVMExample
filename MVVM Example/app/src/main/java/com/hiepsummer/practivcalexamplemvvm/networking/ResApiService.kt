package com.hiepsummer.practivcalexamplemvvm.networking

import com.hiepsummer.practivcalexamplemvvm.model.Video
import com.hiepsummer.practivcalexamplemvvm.model.VideoWrapper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ResApiService {
    @GET("/api/background_resource")
    fun getVideo(): Deferred<VideoWrapper>

    companion object {
        fun createCorService(): ResApiService {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://kaimin.adnet.plus")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(ResApiService::class.java)
        }
    }
}