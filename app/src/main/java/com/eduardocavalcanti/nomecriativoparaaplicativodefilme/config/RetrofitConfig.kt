package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitConfig {

    fun init(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(RetrofitConstants.api_base_url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}