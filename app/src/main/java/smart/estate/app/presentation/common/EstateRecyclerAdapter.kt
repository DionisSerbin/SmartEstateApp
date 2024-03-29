package smart.estate.app.presentation.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.paging.PagingDataAdapter
import smart.estate.app.R
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.model_processing.EstateComparator

class EstateRecyclerAdapter(val viewModel: ViewModel, val context: Context) : PagingDataAdapter<Estate, EstateViewHolder>(
    EstateComparator()
) {
    override fun onBindViewHolder(holder: EstateViewHolder, position: Int) {
        val estate = getItem(position)
        if (estate != null) {
            holder.bind(estate, context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.estate_card_item, parent, false)
        return EstateViewHolder(view, viewModel)
    }
}