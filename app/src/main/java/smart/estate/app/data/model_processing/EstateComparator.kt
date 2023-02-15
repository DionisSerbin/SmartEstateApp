package smart.estate.app.data.model_processing

import androidx.recyclerview.widget.DiffUtil
import smart.estate.app.data.model.Estate

class EstateComparator: DiffUtil.ItemCallback<Estate>() {
    override fun areItemsTheSame(oldItem: Estate, newItem: Estate): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Estate, newItem: Estate): Boolean {
        return oldItem == newItem
    }

}