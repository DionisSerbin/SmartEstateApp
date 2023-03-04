package smart.estate.app.presentation.favourites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import smart.estate.app.R
import smart.estate.app.data.model.Prefs
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.presentation.favourites.viewmodel.FavouritesViewModel
import smart.estate.app.presentation.common.EstateRecyclerAdapter
import smart.estate.app.presentation.common.EstateViewModel

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private lateinit var prefs: Prefs

    private val favouritesViewModel: FavouritesViewModel by viewModels()

    private val estateViewModel: EstateViewModel by activityViewModels()

    private var estates = arrayListOf<Estate>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        estateViewModel.saveIdReturned(R.layout.fragment_favourites)

        val estateRecyclerAdapter = FavouriteRecyclerAdapter(estates, estateViewModel)

        view.findViewById<RecyclerView>(R.id.favourites_estate_recycler_view).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = estateRecyclerAdapter
        }

        val progressDialog = view.findViewById<ProgressBar>(R.id.progressDialog)


        viewLifecycleOwner.lifecycleScope.launch {
            progressDialog.isVisible = true
            prefs = context?.let { Prefs(it) }!!
            val responseSuccess = withContext(Dispatchers.IO) {
                async {
                    favouritesViewModel.getFavouriteEstates(prefs.getMail()!!)
                }
            }.await()
            if (responseSuccess == null) {
                Toast.makeText(
                    context,
                    "Возникла ошибка",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                progressDialog.isVisible = false
                estateRecyclerAdapter.updateDigits(responseSuccess.toMutableList())
            }
        }
    }

    companion object {
        const val DELAY_TIME: Long = 500
        @JvmStatic
        fun newInstance() = FavouritesFragment()
    }
}