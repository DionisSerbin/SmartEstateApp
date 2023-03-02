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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import smart.estate.app.R
import smart.estate.app.presentation.favourites.viewmodel.FavouritesViewModel
import smart.estate.app.presentation.common.EstateRecyclerAdapter
import smart.estate.app.presentation.common.EstateViewModel

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private val favouritesViewModel: FavouritesViewModel by viewModels()

    private val estateViewModel: EstateViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        estateViewModel.saveIdReturned(R.layout.fragment_favourites)

        val estateRecyclerAdapter = EstateRecyclerAdapter(estateViewModel)

        view.findViewById<RecyclerView>(R.id.favourites_estate_recycler_view).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = estateRecyclerAdapter
        }

        val progressDialog = view.findViewById<ProgressBar>(R.id.progressDialog)

        estateRecyclerAdapter.addLoadStateListener { loadState ->
            if ((loadState.refresh is LoadState.Loading)||(loadState.append is LoadState.Loading)){
                progressDialog.isVisible = true
            } else {
                viewLifecycleOwner.lifecycleScope.launch{
                    delay(DELAY_TIME)
                    progressDialog.isVisible = false
                }
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch{
            favouritesViewModel.getEstates().observe(viewLifecycleOwner){
                it?.let {
                    estateRecyclerAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    companion object {

        const val DELAY_TIME: Long = 500
        @JvmStatic
        fun newInstance() = FavouritesFragment()
    }
}