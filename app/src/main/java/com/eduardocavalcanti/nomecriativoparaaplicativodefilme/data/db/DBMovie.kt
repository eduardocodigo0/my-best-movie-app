package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorites")
data class DBMovie(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val poster: String,

    val overView: String,

    val releaseDate: String,

    val score: Double,

    val adult: Boolean

):Serializable
