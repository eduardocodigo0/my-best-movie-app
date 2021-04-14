package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.R
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Result
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.EduUtils
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val movieTitle = itemView.findViewById<TextView>(R.id.tv_movie_title)
    private val movieScore = itemView.findViewById<TextView>(R.id.tv_score_movie)
    private val buttonDetails = itemView.findViewById<Button>(R.id.bt_details)

    fun bind(results: Result?) {
        if (results != null) {

            Picasso.get().load(EduUtils.createPosterPath(results.posterPath))
                    .into(itemView.findViewById<ImageView>(R.id.iv_movie_poster))

            movieTitle.text = results.title
            movieScore.text = results.voteAverage.toString()
            buttonDetails.setOnClickListener {

                val myBundle = Bundle()
                myBundle.putSerializable(EduUtils.getBundleKey(), results)

                Navigation.findNavController(itemView).navigate(R.id.action_movieListFragment_to_movieDetailFragment, myBundle)

            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_list_item, parent, false)

            return MovieViewHolder(view)
        }
    }

}