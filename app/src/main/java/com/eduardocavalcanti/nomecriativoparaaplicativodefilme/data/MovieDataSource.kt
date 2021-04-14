package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.NetState
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.RetrofitConstants
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.service.MovieService
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


class MovieDataSource(
        private val networkService: MovieService,
        private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Result>() {


    var state: MutableLiveData<NetState> = MutableLiveData()
    private var retryCompletable: Completable? = null


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {
        updateState(NetState.LOADING)
        compositeDisposable.add(
                networkService.getMovies(RetrofitConstants.api_key, 1).subscribe(
                        { response ->
                            updateState(NetState.DONE)
                            callback.onResult(response.results,
                                    null,
                                    2)

                        },
                        {
                            updateState(NetState.ERROR)
                            setRetry(Action { loadInitial(params, callback) })
                        }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        updateState(NetState.LOADING)
        compositeDisposable.add(
                networkService.getMovies(RetrofitConstants.api_key, params.key).subscribe(
                        { response ->
                            updateState(NetState.DONE)
                            callback.onResult(
                                    response.results,
                                    params.key + 1
                            )

                        },
                        {
                            updateState(NetState.ERROR)
                            setRetry(Action { loadAfter(params, callback) })
                        }

                )
        )
    }

    private fun updateState(state: NetState) {
        this.state.postValue(state)
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }

}