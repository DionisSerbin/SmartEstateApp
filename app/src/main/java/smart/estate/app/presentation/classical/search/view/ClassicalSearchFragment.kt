package smart.estate.app.presentation.classical.search.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import smart.estate.app.R

class ClassicalSearchFragment : Fragment(R.layout.fragment_classical_search) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_classical_search, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClassicalSearchFragment()

    }
}