package smart.estate.app.presentation.common

import android.content.Context
import android.location.Geocoder
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import smart.estate.app.R
import smart.estate.app.data.model.Prefs
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.model_processing.TextProcessor
import java.util.*

class EstateViewHolder(val view: View, val viewModel: ViewModel) : RecyclerView.ViewHolder(view) {

    private lateinit var prefs: Prefs

    private val costEstateTextView by lazy { view.findViewById<TextView>(R.id.estate_cost_value_text_view) }

    private val totalAreaEstateTextView by lazy { view.findViewById<TextView>(R.id.total_area_text_view) }

    private val addressEstateTextView by lazy { view.findViewById<TextView>(R.id.address_text_view) }

    private val timePublishedEstateTextView by lazy { view.findViewById<TextView>(R.id.time_published_text_view) }

    private val favouriteButton by lazy { view.findViewById<MaterialButton>(R.id.favourite_button) }

    private val pageNumber by lazy { view.findViewById<TextView>(R.id.page_number) }

    private val estateCard by lazy { view.findViewById<CardView>(R.id.estate_card_item) }

    private val viewPager by lazy { view.findViewById<ViewPager2>(R.id.photos_view_pager) }

    fun bind(estate: Estate, context: Context) {
        estateCard.setOnClickListener {
            (viewModel as EstateViewModel).saveEstate(estate)
            when (viewModel.idReturned.value) {
                R.layout.fragment_classical_search -> findNavController(estateCard).navigate(R.id.action_navigation_classical_search_to_classicalSearchEstateFragment)
                R.layout.fragment_favourites -> findNavController(estateCard).navigate(R.id.action_navigation_favourites_to_favouritesEstateFragment)
                R.layout.fragment_user_estates -> findNavController(estateCard).navigate(R.id.action_userEstatesFragment_to_userEstateFragment)
            }
        }

        favouriteButton.setOnClickListener {
            view.findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
                val responseSuccess = withContext(Dispatchers.IO) {
                    async {
                        prefs = view.context?.let { Prefs(it) }!!
                        (viewModel as EstateViewModel).addToFavourite(prefs.getMail()!!, estate.id)
                    }
                }.await()
                if (responseSuccess == null) {
                    Toast.makeText(
                        view.context,
                        "Возникла ошибка",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        view.context,
                        "Недвижимость ${estate.id} добавлена на ${prefs.getMail()!!}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
//        addressEstateTextView.text = "${estate.longitude} + ${estate.latitude} + ${estate.region}"
        addressEstateTextView.text = "..."
        costEstateTextView.text = TextProcessor().convertCostToNiceText(estate.price)
        totalAreaEstateTextView.text = TextProcessor().convertAreaToNiceText(estate.totalArea)

        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(
            estate.latitude.toDouble(),
            estate.longitude.toDouble(),
            1
        )
        addressEstateTextView.text = "${addresses?.get(0)?.locality}, ${addresses?.get(0)?.getAddressLine(0)}"

        timePublishedEstateTextView.text = TextProcessor().convertDateToNiceText(
            day = estate.day,
            month = estate.month,
            year = estate.year,
            time = estate.time
        )
        pageNumber.isVisible = false

        val estateViewPagerAdapter = EstateViewPagerAdapter(estate.photos)

        viewPager.adapter = estateViewPagerAdapter

        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}