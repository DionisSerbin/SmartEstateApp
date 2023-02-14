package smart.estate.app.presentation.classical.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import smart.estate.app.R
import smart.estate.app.data.model.Estate
import smart.estate.app.data.model_processing.ClassicalSearchComparator
import smart.estate.app.presentation.classical.search.view.ClassicalSearchViewHolder

class ClassicalSearchPagerAdapter : PagingDataAdapter<Estate, ClassicalSearchViewHolder>(
    ClassicalSearchComparator()
) {
    override fun onBindViewHolder(holder: ClassicalSearchViewHolder, position: Int) {
        val estate = getItem(position)
        if (estate != null) {
            holder.bind(estate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassicalSearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.estate_card_item, parent, false)
        return ClassicalSearchViewHolder(view)
    }
}