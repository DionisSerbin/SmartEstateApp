package smart.estate.app.presentation.user.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import smart.estate.app.R
import smart.estate.app.data.model.Prefs
import smart.estate.app.presentation.sign.view.SignInActivity

class UserFragment : Fragment(R.layout.fragment_user) {

    private lateinit var prefs: Prefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = context?.let { Prefs(it) }!!

        val userNameTextView = view.findViewById<TextView>(R.id.user_name_text_view)
        val userLoginTextView = view.findViewById<TextView>(R.id.user_login_text_view)
        val userMailTextView = view.findViewById<TextView>(R.id.user_mail_text_view)

        val nameSP = prefs.getName()
        val loginSP = prefs.getLogin()
        val mailSP = prefs.getMail()

        if (nameSP != null) {
            userNameTextView.text = nameSP
        }
        if (loginSP != null) {
            userLoginTextView.text = loginSP
        }
        if (mailSP != null) {
            userMailTextView.text = mailSP
        }

        val yourEstatesButton = view.findViewById<MaterialButton>(R.id.your_estates_button)
        yourEstatesButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_user_to_userEstatesFragment)
        }

        val exitButton = view.findViewById<MaterialButton>(R.id.log_out_button)
        exitButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(exitButton.context, SignInActivity::class.java)
            startActivity(intent)
        }

        val settingsButton = view.findViewById<MaterialButton>(R.id.settings_button)
        settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_user_to_userSettingsFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()
    }
}