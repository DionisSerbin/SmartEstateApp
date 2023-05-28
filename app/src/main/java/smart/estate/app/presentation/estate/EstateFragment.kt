package smart.estate.app.presentation.estate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import smart.estate.app.R
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.model_processing.LocationGeocoder
import smart.estate.app.data.model_processing.TextProcessor
import smart.estate.app.presentation.common.EstateViewModel
import smart.estate.app.presentation.common.EstateViewPagerAdapter
import java.io.Serializable


abstract class EstateFragment : Fragment(R.layout.fragment_estate) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val estateViewModel = ViewModelProvider(requireActivity()).get(EstateViewModel::class.java)

        val estate = estateViewModel.estate.value!!

        val viewPager = view.findViewById<ViewPager2>(R.id.photos_view_pager_estate)
        val costTextView = view.findViewById<TextView>(R.id.estate_cost_value_text_view)
        val timePublishedTextView =
            view.findViewById<TextView>(R.id.estate_time_published_text_view)
        val totalAreaTextView = view.findViewById<TextView>(R.id.estate_total_area_text_view)
        val addressTextView = view.findViewById<TextView>(R.id.estate_address_text_view)
        val houseTypeTextView = view.findViewById<TextView>(R.id.estate_type_house_text_view)
        val levelTextView = view.findViewById<TextView>(R.id.estate_level_text_view)
        val levelsTextView = view.findViewById<TextView>(R.id.estate_levels_text_view)
        val numberOfRooms = view.findViewById<TextView>(R.id.estate_rooms_text_view)
        val kitchenAreaTextView = view.findViewById<TextView>(R.id.estate_kitchen_area_text_view)

        costTextView.text = TextProcessor().convertCostToNiceText(estate.price)
        timePublishedTextView.text = TextProcessor().convertDateToNiceText(
            day = estate.day,
            month = estate.month,
            year = estate.year,
            time = estate.time
        )
        //TODO add objecttype
        totalAreaTextView.text =
            TOTAL_AREA_STRING + TextProcessor().convertAreaToNiceText(estate.totalArea)
        addressTextView.text = ADDRESS_STRING + LocationGeocoder().getAddress(
            context = requireContext(),
            latitude = estate.latitude,
            longitude = estate.longitude
        )
        houseTypeTextView.text = HOUSE_TYPE_STRING + HOUSE_TYPE[estate.buildingType]
        levelTextView.text = LEVEL_STRING + estate.level.toString()
        levelsTextView.text = LEVELS_STRING + estate.levels.toString()
        numberOfRooms.text = NUMBER_OF_ROOMS_STRING + estate.rooms.toString()
        kitchenAreaTextView.text =
            KITCHEN_AREA_STRING + TextProcessor().convertAreaToNiceText(estate.kitchenArea)
        val estateViewPagerAdapter = EstateViewPagerAdapter(estate.photos)
        viewPager.adapter = estateViewPagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val previousPageButton = view.findViewById<MaterialButton>(R.id.estate_previous_page_button)

        previousPageButton.setOnClickListener {
            when (estateViewModel.idReturned.value) {
                R.layout.fragment_classical_search -> findNavController().navigate(R.id.action_classicalSearchEstateFragment_to_navigation_classical_search)
                R.layout.fragment_favourites -> findNavController().navigate(R.id.action_favouritesEstateFragment_to_navigation_favourites)
                R.layout.fragment_user_estates -> findNavController().navigate(R.id.action_userEstateFragment_to_userEstatesFragment)
            }
        }
    }

    companion object {
        private const val ADDRESS_STRING = "Адрес: "
        private const val TOTAL_AREA_STRING = "Площадь: "
        private const val KITCHEN_AREA_STRING = "Площадь кухни: "
        private const val LEVEL_STRING = "Этаж: "
        private const val LEVELS_STRING = "Всего этажей: "
        private const val NUMBER_OF_ROOMS_STRING = "Комнат: "
        private const val HOUSE_TYPE_STRING = "Тип дома: "
        private val HOUSE_TYPE = mapOf<Int, String>(
            0 to "Прочее",
            1 to "Панельный дом",
            2 to "Монолит",
            3 to "Кирпичный",
            4 to "Блочный",
            5 to "Деревянный"
        )
    }
}