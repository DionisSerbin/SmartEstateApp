package smart.estate.app.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import smart.estate.app.R
import smart.estate.app.data.model.estate.DataClass
import smart.estate.app.data.model_processing.EstateComparator

class EstateRecyclerAdapter : PagingDataAdapter<DataClass, EstateViewHolder>(
    EstateComparator()
) {
    override fun onBindViewHolder(holder: EstateViewHolder, position: Int) {
        val estate = getItem(position)
        if (estate != null) {
            holder.bind(estate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.estate_card_item, parent, false)
        return EstateViewHolder(view)
    }
}