package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.service

import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.RetrofitConfig
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Movies
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getMovies(
            @Query("api_key") api_key: String,
            @Query("page") page: Int): Single<Movies>

    @GET("search/movie")
    fun getSearch(
            @Query("api_key") api_key: String,
            @Query("page") page: Int,
            @Query("query") query: String): Call<Movies>

    companion object {
        fun getService(): MovieService {

            val retrofit = RetrofitConfig().init()
            return retrofit.create(MovieService::class.java)

        }
    }


}