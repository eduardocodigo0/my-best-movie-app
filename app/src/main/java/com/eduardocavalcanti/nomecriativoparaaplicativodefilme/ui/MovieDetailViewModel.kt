package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db.DBMovie
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db.DBMovieRepository


class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val mIsFavorite: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = mIsFavorite

    private var repository: DBMovieRepository = DBMovieRepository(application)


    init {
        mIsFavorite.value = false
    }


    suspend fun saveFavorite(dbMovie: DBMovie) {

        if (repository.saveFavorite(dbMovie)) {
            this.mIsFavorite.value = true
        }

    }

    suspend fun removeFavorite(dbMovie: DBMovie) {

        if (repository.removeFavorite(dbMovie)) {
            this.mIsFavorite.value = false
        }

    }

    fun getIsFavorite(): Boolean {
        return mIsFavorite.value ?: false
    }

    suspend fun getIsFavoriteFromDB(title: String) {
        mIsFavorite.value = repository.getIsFavoriteFromDB(title)
    }

}