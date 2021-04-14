package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config

import android.net.Uri

class EduUtils private constructor(){
    companion object{

        private val CONSTANTS = object {
            val bundle_key = "movie_item"
            val base_poster_path = "https://www.themoviedb.org/t/p/w500/"
            val background_video_file_name = "firespark4"
        }


        fun createPosterPath(poster: String):String{
            return CONSTANTS.base_poster_path.plus(poster)
        }

        fun getBundleKey() = CONSTANTS.bundle_key

        fun getBackgroundVideoURI(packageName:String): Uri {
            return Uri.parse("android.resource://$packageName/raw/${CONSTANTS.background_video_file_name}")
        }


    }
}