package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.NetState
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.*
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.service.MovieService
import io.reactivex.disposables.CompositeDisposable


class MovieListViewModel : ViewModel() {

    private val movieService = MovieService.getService()
    var movieList: LiveData<PagedList<Result>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private var movieDataSourceFactory: MovieDataSourceFactory

    init {
        movieDataSourceFactory = MovieDataSourceFactory(compositeDisposable, movieService)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()

        movieList = LivePagedListBuilder<Int, Result>(movieDataSourceFactory, config).build()
    }


    fun getState(): LiveData<NetState> = Transformations.switchMap<MovieDataSource, NetState>(movieDataSourceFactory.movieDataSourceLiveData, MovieDataSource::state)

    fun retry() {
        movieDataSourceFactory.movieDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean = movieList.value?.isEmpty() ?: true

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }
}