package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data


import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db.DBMovie
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Result(
        @SerializedName("adult")
        val adult: Boolean,

        @SerializedName("overview")
        val overview: String,

        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,

        @SerializedName("vote_average")
        val voteAverage: Double,

        ) : Serializable {

    fun hasEmptyField(): Boolean = title.isNullOrEmpty() ||
            posterPath.isNullOrEmpty() ||
            releaseDate.isNullOrEmpty() ||
            overview.isNullOrEmpty()

    fun convertToDBMovie(): DBMovie {
        return DBMovie(
                title = this.title,
                overView = this.overview,
                releaseDate = this.releaseDate,
                score = this.voteAverage,
                adult = this.adult,
                poster = this.posterPath
        )
    }

}