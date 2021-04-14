package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db.DBMovie
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db.DBMovieRepository


class FavoriteMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: DBMovieRepository = DBMovieRepository(application)
    private var mFavoriteMovies: MutableLiveData<List<DBMovie>> = MutableLiveData<List<DBMovie>>()
    val favoriteMovies: LiveData<List<DBMovie>> get() = mFavoriteMovies


    suspend fun getFavorites() {

        mFavoriteMovies.value = repository.getFavorites()

    }

}