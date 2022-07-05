package com.doyoonkim.unsplashpracticerevised.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doyoonkim.unsplashpracticerevised.data.SearchResult
import com.doyoonkim.unsplashpracticerevised.databinding.ResultItemBinding

class ResultListAdapter(private val context: Context): RecyclerView.Adapter<ResultListVH>() {
    var data = listOf<SearchResult.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultListVH {
        val binding = ResultItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ResultListVH(binding)
    }

    override fun onBindViewHolder(holder: ResultListVH, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class ResultListVH(val binding: ResultItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: SearchResult.Result) {
        Log.d("Adapter/", "onBind Called")
        binding.data = data
    }
}
