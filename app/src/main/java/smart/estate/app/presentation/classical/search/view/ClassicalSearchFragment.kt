package smart.estate.app.presentation.classical.search.view

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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import smart.estate.app.R
import smart.estate.app.presentation.common.EstateViewModel
import smart.estate.app.presentation.common.EstateRecyclerAdapter
import smart.estate.app.presentation.classical.search.viewmodel.ClassicalSearchViewModel
import smart.estate.app.presentation.classical.search.viewmodel.SaveFiltersViewModel


@AndroidEntryPoint
class ClassicalSearchFragment : Fragment(R.layout.fragment_classical_search) {

    private val classicalSearchViewModel: ClassicalSearchViewModel by viewModels()

    private val estateViewModel: EstateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_classical_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        estateViewModel.saveIdReturned(R.layout.fragment_classical_search)

        val estateRecyclerAdapter = EstateRecyclerAdapter(estateViewModel, requireContext())

        val filtersButton = view.findViewById<MaterialButton>(R.id.filter_button)

        filtersButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_classical_search_to_filtersClassicalSearchFragment)
        }

        val estateFiltersViewModel =
            ViewModelProvider(requireActivity()).get(SaveFiltersViewModel::class.java)

        view.findViewById<RecyclerView>(R.id.classical_estate_recycler_view).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = estateRecyclerAdapter
        }

        val progressDialog = view.findViewById<ProgressBar>(R.id.progressDialog)

        estateRecyclerAdapter.addLoadStateListener { loadState ->
            if ((loadState.refresh is LoadState.Loading) || (loadState.append is LoadState.Loading)) {
                progressDialog.isVisible = true
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
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

        val clearFiltersButton = view.findViewById<MaterialButton>(R.id.clear_filters_button)

        clearFiltersButton.setOnClickListener{
            estateFiltersViewModel.estateFilters.value = null
        }

        viewLifecycleOwner.lifecycleScope.launch {
            if (estateFiltersViewModel.estateFilters.value == null) {
                classicalSearchViewModel.getEstates().observe(viewLifecycleOwner) {
                    it?.let {
                        estateRecyclerAdapter.submitData(lifecycle, it)
                    }
                }
            } else {
                classicalSearchViewModel.getEstatesWhere(estateFiltersViewModel.estateFilters.value!!).observe(viewLifecycleOwner) {
                    it?.let {
                        estateRecyclerAdapter.submitData(lifecycle, it)
                    }
                }
            }
        }
    }

    companion object {

        const val DELAY_TIME: Long = 1000

        @JvmStatic
        fun newInstance() = ClassicalSearchFragment()

    }
}