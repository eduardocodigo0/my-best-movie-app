package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.R
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.EduUtils
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Result


class SearchRecyclerViewAdapter(private val resultList: List<Result>, private val myContext: Context) : RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        val title: TextView = itemView.findViewById(R.id.tv_movie_title)
        val textScore: TextView = itemView.findViewById(R.id.tv_score_movie)
        val buttonMoreDetails: Button = itemView.findViewById(R.id.bt_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = resultList[position]

        Glide.with(myContext).load(EduUtils.createPosterPath(currentItem.posterPath)).into(holder.poster)

        holder.title.text = currentItem.title
        holder.textScore.text = currentItem.voteAverage.toString()

        holder.buttonMoreDetails.setOnClickListener {

            val myBundle = Bundle()
            myBundle.putSerializable(EduUtils.getBundleKey(), currentItem)

            Navigation.findNavController(holder.itemView).navigate(R.id.action_searchFragment_to_movieDetailFragment, myBundle)

        }

    }

    override fun getItemCount(): Int {
        return resultList.size
    }
}