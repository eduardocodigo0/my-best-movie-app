package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.R
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.adapter.MoviePagedListAdapter
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Result


class MovieListFragment : Fragment() {

    private lateinit var movieListAdapter: MoviePagedListAdapter
    private lateinit var viewModel: MovieListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var progressBar: ProgressBar


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        setHasOptionsMenu(true)

        recycler_view = view.findViewById(R.id.rv_main_movie_list)
        progressBar = view.findViewById(R.id.pb_popular)

        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        initAdapter()

        return view
    }

    private var observer = Observer<PagedList<Result>> {
        movieListAdapter.submitList(it)
        progressBar.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
    }

    private fun initAdapter() {
        movieListAdapter = MoviePagedListAdapter { viewModel.retry() }
        recycler_view.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recycler_view.adapter = movieListAdapter
        viewModel.movieList.observe(viewLifecycleOwner, observer)
    }


}