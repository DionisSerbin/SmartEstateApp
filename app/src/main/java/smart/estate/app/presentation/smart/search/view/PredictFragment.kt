package smart.estate.app.presentation.smart.search.view

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import smart.estate.app.R
import smart.estate.app.presentation.smart.search.viewmodel.PredictViewModel
import smart.estate.app.presentation.smart.search.viewmodel.SmartSearchViewModel

@AndroidEntryPoint
class PredictFragment : Fragment() {

    private lateinit var frameAnimation: AnimationDrawable

    private val predictViewModel: PredictViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_predict, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadingImageView = view.findViewById<ImageView>(R.id.predicting_animation)
        loadingImageView.setBackgroundResource(R.drawable.predict_loading)
        frameAnimation = loadingImageView.background as AnimationDrawable
        frameAnimation.start()

        val smartSearchViewModel =
            ViewModelProvider(requireActivity()).get(SmartSearchViewModel::class.java)

        val smartEstateParameters = smartSearchViewModel.estateParameters.value

        val previousPageButton =
            view.findViewById<MaterialButton>(R.id.smart_search_previous_page_button)

        previousPageButton.setOnClickListener {
            smartSearchViewModel.saveNullEstateParameters()
            findNavController().navigate(R.id.action_predictFragment_to_navigation_smart_search_home)
        }


        viewLifecycleOwner.lifecycleScope.launch {
            try {
                delay(1000)
                val pair = withContext(Dispatchers.IO) {
                    async {
                        predictViewModel.getPrediction(smartEstateParameters!!)
                    }
                }

                smartSearchViewModel.saveCostPredictedPair(pair.await()!!)
                withContext(Dispatchers.Main) {
                    findNavController().navigate(R.id.action_predictFragment_to_estateCostPredictedFragment)
                }
            } catch (error: Throwable) {
                error.printStackTrace()
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = PredictFragment()
    }
}