package smart.estate.app.presentation.add.estate.view

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import smart.estate.app.R
import smart.estate.app.data.model.request.User
import smart.estate.app.presentation.add.estate.viewmodel.AddEstateViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@AndroidEntryPoint
class AddingEstateFragment : Fragment(R.layout.fragment_adding_estate) {

    private lateinit var frameAnimation: AnimationDrawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adding_estate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val successImageView = view.findViewById<ImageView>(R.id.adding_complete_succes_image_view)

        val errorImageView = view.findViewById<ImageView>(R.id.adding_complete_error_image_view)
        successImageView.isVisible = false
        errorImageView.isVisible = false

        val loadingImageView = view.findViewById<ImageView>(R.id.predicting_animation)
        loadingImageView.setBackgroundResource(R.drawable.predict_loading)
        frameAnimation = loadingImageView.background as AnimationDrawable
        frameAnimation.start()

        val addEstateViewModel =
            ViewModelProvider(requireActivity()).get(AddEstateViewModel::class.java)


        val addedEstate = addEstateViewModel.addedEstate.value

        val previousPageButton =
            view.findViewById<MaterialButton>(R.id.add_estate_previous_page_button)

        previousPageButton.setOnClickListener {
            findNavController().navigate(R.id.action_addingEstateFragment_to_navigation_add_estate)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val responseSuccess = withContext(Dispatchers.IO) {
                    async {
                        addEstateViewModel.createEstate(addedEstate!!)
                    }
                }.await()
                frameAnimation.stop()
                if (responseSuccess == null) {
                    Toast.makeText(
                        context,
                        "Возникла ошибка",
                        Toast.LENGTH_SHORT
                    ).show()
                    errorImageView.isVisible = true
                    findNavController().navigate(R.id.action_addingEstateFragment_to_navigation_add_estate)
                } else {
                    successImageView.isVisible = true
                    findNavController().navigate(R.id.action_addingEstateFragment_to_navigation_add_estate)
                }
            } catch (error: Throwable) {
                error.printStackTrace()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = AddingEstateFragment()
    }
}