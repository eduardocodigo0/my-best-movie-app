package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.adapter


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.R
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Result
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db.DBMovie
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui.MovieListFragmentDirections
import com.squareup.picasso.Picasso

class MovieRecyclerViewAdapter( private val resultList: List<DBMovie>, private val myContext:Context): RecyclerView.Adapter<MovieRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val poster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        val title: TextView = itemView.findViewById(R.id.tv_movie_title)
        val imgScore: ImageView = itemView.findViewById(R.id.iv_score_movie)
        val textScore: TextView = itemView.findViewById(R.id.tv_score_movie)
        val buttonMoreDetails: Button = itemView.findViewById(R.id.bt_details)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.movie_list_item,parent,false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = resultList[position]


        Picasso.get().load("https://www.themoviedb.org/t/p/w500/${currentItem.poster}")
            .into(holder.poster)

        holder.title.text = currentItem.title
        holder.textScore.text = currentItem.score.toString()

        holder.buttonMoreDetails.setOnClickListener {

            val res: Result = Result(
                voteAverage = currentItem.score,
                posterPath = currentItem.poster,
                title = currentItem.title,
                overview = currentItem.overView,
                adult = currentItem.adult,
                releaseDate = currentItem.releaseDate

            )
            var myBundle = Bundle()
            myBundle.putSerializable("movie_item", res)

            Navigation.findNavController(holder.itemView).navigate(R.id.action_favoriteMoviesFragment_to_movieDetailFragment, myBundle)

        }

    }

    override fun getItemCount(): Int {
        return resultList.size
    }
}