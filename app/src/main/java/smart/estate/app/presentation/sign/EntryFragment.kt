package smart.estate.app.presentation.sign


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import smart.estate.app.R


class EntryFragment : Fragment(R.layout.fragment_entry) {

    interface FragmentCreation {
        fun createSignInFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signButton = view.findViewById<MaterialButton>(R.id.sign_in_button)

        signButton.setOnClickListener {
            (activity as FragmentCreation).createSignInFragment()
        }
    }

}