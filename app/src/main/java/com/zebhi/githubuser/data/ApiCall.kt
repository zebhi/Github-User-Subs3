package com.zebhi.githubuser.data

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.zebhi.githubuser.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiCall {
    private val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(60 * 5, TimeUnit.SECONDS)
        .readTimeout(60 * 5, TimeUnit.SECONDS)
        .writeTimeout(60 * 5, TimeUnit.SECONDS)

    private val loggingInterceptor = HttpLoggingInterceptor()

    private fun retrofit(): Retrofit? {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.addNetworkInterceptor(StethoInterceptor())

        return Retrofit.Builder()
            .client(okHttpClient.build())
            .baseUrl(BuildConfig.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun instance(): ApiInterface = retrofit()!!.create<ApiInterface>(ApiInterface::class.java)
}
