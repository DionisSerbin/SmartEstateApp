package smart.estate.app.data.model_processing

import androidx.recyclerview.widget.DiffUtil
import smart.estate.app.data.model.estate.DataClass

class EstateComparator: DiffUtil.ItemCallback<DataClass>() {
    override fun areItemsTheSame(oldItem: DataClass, newItem: DataClass): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataClass, newItem: DataClass): Boolean {
        return oldItem == newItem
    }

}