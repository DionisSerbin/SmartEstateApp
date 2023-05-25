package smart.estate.app.presentation.favourites.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import smart.estate.app.R
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.presentation.common.EstateViewHolder

class FavouriteRecyclerAdapter(var estates: MutableList<Estate>, val viewModel: ViewModel, val context: Context) :
    RecyclerView.Adapter<EstateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.estate_card_item, parent, false)
        return EstateViewHolder(view, viewModel)
    }

    override fun onBindViewHolder(holder: EstateViewHolder, position: Int) {
        holder.bind(estates[position], context)
    }

    override fun getItemCount() = estates.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateDigits(newEstates: MutableList<Estate>) {
        estates = newEstates
        notifyDataSetChanged()
    }
}