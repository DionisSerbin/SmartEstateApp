package smart.estate.app.presentation.classical.search.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import smart.estate.app.R
import smart.estate.app.data.model.Estate

class ClassicalSearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val costEstateTextView by lazy { view.findViewById<TextView>(R.id.estate_cost_value_text_view) }

    private val totalAreaEstateTextView by lazy { view.findViewById<TextView>(R.id.total_area_text_view) }

    private val addressEstateTextView by lazy { view.findViewById<TextView>(R.id.address_text_view) }

    private val timePublishedEstateTextView by lazy { view.findViewById<TextView>(R.id.time_published_text_view) }



    fun bind(estate: Estate) {
        costEstateTextView.text = estate.price.toString()
        totalAreaEstateTextView.text = estate.totalArea.toString()
        addressEstateTextView.text = "${estate.longitude} + ${estate.latitude} + ${estate.region}"



        ¬

    }
}