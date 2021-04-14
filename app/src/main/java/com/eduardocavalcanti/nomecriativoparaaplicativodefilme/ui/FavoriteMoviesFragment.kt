package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.R
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.adapter.MovieRecyclerViewAdapter
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db.DBMovie
import kotlinx.coroutines.launch


class FavoriteMoviesFragment : Fragment() {

    private lateinit var viewModel: FavoriteMoviesViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_movies, container, false)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application()).create(FavoriteMoviesViewModel::class.java)
        recycler_view = view.findViewById(R.id.rv_favorite_movie_list)
        progressBar = view.findViewById(R.id.pb_favorite)

        viewModel.favoriteMovies.observe(viewLifecycleOwner, observer)

        updateFavorites()

        return view
    }

    fun updateFavorites() {
        lifecycleScope.launch {
            viewModel.getFavorites()
        }
    }

    private val observer = Observer<List<DBMovie>> { movieList ->

        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MovieRecyclerViewAdapter(movieList, requireActivity().applicationContext)
        }

        progressBar.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE

    }
}