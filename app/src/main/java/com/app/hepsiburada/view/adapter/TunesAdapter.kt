package com.app.hepsiburada.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.hepsiburada.R
import com.app.hepsiburada.core.base.IBaseClickListener
import com.app.hepsiburada.databinding.ItemReyclerviewBinding
import com.app.hepsiburada.model.TunesModel
import com.app.hepsiburada.view.fragment.MainFragmentDirections

class TunesAdapter(var data: List<TunesModel>?) :
    RecyclerView.Adapter<TunesAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding: ItemReyclerviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_reyclerview, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        if (data != null) {
            holder.bind(data!![position])
        }
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    class ArticleViewHolder(val binding: ItemReyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root), IBaseClickListener<TunesModel> {

        fun bind(item: TunesModel) {
            binding.itemClickListener = this
            binding.model = item
            binding.executePendingBindings()
        }

        override fun onClick(t: TunesModel) {
            t?.let {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(t.trackId)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }


    }
}



