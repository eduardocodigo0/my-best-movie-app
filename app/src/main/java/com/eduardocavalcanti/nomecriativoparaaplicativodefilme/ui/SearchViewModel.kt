package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.RetrofitConfig
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.RetrofitConstants
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Movies
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Result
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.extensions.cleanNullData
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.service.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {


    private val mMovieList: MutableLiveData<List<Result>> = MutableLiveData()
    val movieList: LiveData<List<Result>> get() = mMovieList

    fun getMovies(movieName: String) {

        val retrofitClient = RetrofitConfig().init()
        val endpoint = retrofitClient.create(MovieService::class.java)

        val callback = endpoint.getSearch(RetrofitConstants.api_key, 1, movieName)

        callback.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {

                mMovieList.value = response.body()?.results?.cleanNullData()

            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.i("RETRO", "ERRO NA REQUISIÇÃO!")
            }


        })

    }


}



