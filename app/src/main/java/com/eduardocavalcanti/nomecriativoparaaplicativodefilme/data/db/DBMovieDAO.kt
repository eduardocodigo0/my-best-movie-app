package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DBMovieDAO {

    @Insert
    suspend fun insert(movie: DBMovie)

    @Query("DELETE FROM favorites WHERE title = :title")
    suspend fun delete(title: String)

    @Query("SELECT * FROM favorites")
    suspend fun getAllMovies():List<DBMovie>

    @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE title = :title)")
    suspend fun isThisFavorite(title: String): Boolean

}