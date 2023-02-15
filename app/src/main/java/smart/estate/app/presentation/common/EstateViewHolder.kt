package smart.estate.app.presentation.common

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import smart.estate.app.R
import smart.estate.app.data.model.Estate

class EstateViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val costEstateTextView by lazy { view.findViewById<TextView>(R.id.estate_cost_value_text_view) }

    private val totalAreaEstateTextView by lazy { view.findViewById<TextView>(R.id.total_area_text_view) }

    private val addressEstateTextView by lazy { view.findViewById<TextView>(R.id.address_text_view) }

    private val timePublishedEstateTextView by lazy { view.findViewById<TextView>(R.id.time_published_text_view) }

    private val pageNumber by lazy { view.findViewById<TextView>(R.id.page_number) }

    private val viewPager by lazy { view.findViewById<ViewPager2>(R.id.photos_view_pager) }

    fun bind(estate: Estate) {
        costEstateTextView.text = estate.price.toString()
        totalAreaEstateTextView.text = estate.totalArea.toString()
        addressEstateTextView.text = "${estate.longitude} + ${estate.latitude} + ${estate.region}"
        timePublishedEstateTextView.text = "${estate.time}"
        pageNumber.text = "${estate.id}"

        val estateViewPagerAdapter = EstateViewPagerAdapter(estate.photos)

        viewPager.adapter = estateViewPagerAdapter

        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}