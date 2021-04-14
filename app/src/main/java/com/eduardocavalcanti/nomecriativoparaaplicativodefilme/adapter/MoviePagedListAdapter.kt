package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.NetState
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Result
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui.MovieViewHolder

class MoviePagedListAdapter(private val retry: () -> Unit)
    :PagedListAdapter<Result, RecyclerView.ViewHolder>(MoviesDiffCallback) {

        private var state = NetState.LOADING


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return MovieViewHolder.create(parent)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as MovieViewHolder).bind(getItem(position))
        }



        companion object{

            val MoviesDiffCallback = object :DiffUtil.ItemCallback<Result>(){
                override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.title == newItem.title
                }

                override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem == newItem
                }

            }

        }

    fun setState(state: NetState) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

}