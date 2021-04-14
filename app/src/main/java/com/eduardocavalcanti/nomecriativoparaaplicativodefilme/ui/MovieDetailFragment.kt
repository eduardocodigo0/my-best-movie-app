package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import android.app.Application
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.R
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Result
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db.DBMovie
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.EduUtils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class MovieDetailFragment() : Fragment() {


    private lateinit var movieResult: Result
    private lateinit var actualMovie: DBMovie
    private lateinit var viewModel: MovieDetailViewModel

    private lateinit var star: ImageView
    private lateinit var starLabel: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application()).create(MovieDetailViewModel::class.java)

        val myBundle = arguments
        movieResult = myBundle?.getSerializable(EduUtils.getBundleKey()) as Result

        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        val poster = view.findViewById<ImageView>(R.id.iv_poster)
        val title = view.findViewById<TextView>(R.id.tv_title)
        val overview = view.findViewById<TextView>(R.id.tv_overview)
        val releaseDate = view.findViewById<TextView>(R.id.tv_release)
        val score = view.findViewById<TextView>(R.id.tv_score)
        star = view.findViewById(R.id.iv_favorite_start)
        val back = view.findViewById<ImageView>(R.id.iv_back)
        starLabel = view.findViewById(R.id.tv_label_favorite)

        Picasso.get().load(EduUtils.createPosterPath(movieResult.posterPath))
                .into(poster)

        title.text = movieResult.title
        overview.text = movieResult.overview
        releaseDate.text = movieResult.releaseDate
        score.text = movieResult.voteAverage.toString()

        actualMovie = movieResult.convertToDBMovie()

        viewModel.isFavorite.observe(viewLifecycleOwner, observer)

        back.setOnClickListener(listener)
        star.setOnClickListener(listener)

        lifecycleScope.launch {
            viewModel.getIsFavoriteFromDB(actualMovie.title)
        }

        return view
    }

    private val observer = Observer<Boolean> { favorite ->

        if (favorite) {
            star.setColorFilter(this.resources.getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP)
            starLabel.text = resources.getText(R.string.movie_detail_star_favorited_label)
        } else {
            star.setColorFilter(R.color.light_grey, PorterDuff.Mode.SRC_ATOP)
            starLabel.text = resources.getText(R.string.movie_detail_star_not_favorited_label)
        }
    }

    private val listener = View.OnClickListener { currentView ->
        when (currentView.id) {

            R.id.iv_back -> {
                activity?.onBackPressed()
            }

            R.id.iv_favorite_start -> {
                if (!viewModel.getIsFavorite()) {

                    lifecycleScope.launch {
                        viewModel.saveFavorite(actualMovie)
                    }

                } else {

                    lifecycleScope.launch {
                        viewModel.removeFavorite(actualMovie)
                    }

                }
            }

        }
    }

}