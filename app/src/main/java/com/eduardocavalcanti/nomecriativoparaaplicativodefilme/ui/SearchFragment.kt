package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.R
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.adapter.SearchRecyclerViewAdapter
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Result

class SearchFragment : Fragment() {


    private lateinit var viewModel: SearchViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var progressBar: ProgressBar


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        recycler_view = view.findViewById(R.id.rv_search_movie_list)
        progressBar = view.findViewById(R.id.pb_search)


        viewModel.movieList.observe(viewLifecycleOwner, observer)
        view.findViewById<Button>(R.id.bt_search).setOnClickListener(listener)

        return view
    }

    private val listener = View.OnClickListener { currentView ->

        when (currentView.id) {

            R.id.bt_search -> {

                val text = view?.findViewById<EditText>(R.id.et_search)?.text

                if (!text.isNullOrEmpty()) {
                    recycler_view.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                    viewModel.getMovies(text.toString())
                } else {
                    Toast.makeText(context, R.string.empty_search_request, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val observer = Observer<List<Result>> { movieList ->

        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = SearchRecyclerViewAdapter(movieList, requireActivity().applicationContext)
        }

        recycler_view.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

    }


}