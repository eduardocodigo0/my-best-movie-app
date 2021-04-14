package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.service.MovieService
import io.reactivex.disposables.CompositeDisposable


class MovieDataSourceFactory(
        private val compositeDisposable: CompositeDisposable,
        private val movieService: MovieService
) : DataSource.Factory<Int, Result>() {

    val movieDataSourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Result> {
        val movieDataSource = MovieDataSource(movieService, compositeDisposable)
        movieDataSourceLiveData.postValue(movieDataSource)
        return movieDataSource
    }

}