package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db

import android.app.Application
import java.lang.Exception

class DBMovieRepository(application: Application) {

    private val db = DBMovieRoomDatabase.getDataBaseInstance(application)

    suspend fun saveFavorite(dbMovie: DBMovie): Boolean {

        return try {
            db.movieDao().insert(dbMovie)
            true
        } catch (exception: Exception) {
            false
        }
    }

    suspend fun removeFavorite(dbMovie: DBMovie): Boolean {
        return try {
            db.movieDao().delete(dbMovie.title)

            true
        } catch (exception: Exception) {
            false
        }
    }


    suspend fun getIsFavoriteFromDB(title: String): Boolean {
        return db.movieDao().isThisFavorite(title)
    }


    suspend fun getFavorites() = db.movieDao().getAllMovies()

}