package smart.estate.app.presentation.user.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import smart.estate.app.R
import smart.estate.app.data.model.Prefs
import smart.estate.app.data.model.UserSettings
import smart.estate.app.data.model_processing.Validator

class UserSettingsFragment : Fragment() {

    private lateinit var prefs: Prefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = context?.let { Prefs(it) }!!

        val nameSP = prefs.getName()
        val loginSP = prefs.getLogin()

        val nameInputView =
            view.findViewById<TextInputEditText>(R.id.name_input_edit_text)

        val loginInputView =
            view.findViewById<TextInputEditText>(R.id.login_input_edit_text)

        if (nameSP != null) {
            nameInputView.hint = nameSP
        } else {
            nameInputView.hint = INPUT_NAME
        }
        if (loginSP != null) {
            loginInputView.hint = loginSP
        } else {
            loginInputView.hint = INPUT_LOGIN
        }

        val completeButton = view.findViewById<MaterialButton>(R.id.complete_settings_button)
        completeButton.setOnClickListener {

            val userSettings = UserSettings(
                name = nameInputView.text.toString(),
                login = loginInputView.text.toString()
            )
            if (Validator(
                    dataValidatorInterface = userSettings,
                    view = view,
                    context = context
                ).isValidate()
            ) {
                prefs.putName(nameInputView.text.toString())
                prefs.putLogin(loginInputView.text.toString())
                Toast.makeText(
                    context,
                    nameInputView.text.toString() + loginInputView.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_userSettingsFragment_to_navigation_user)
            }
        }
    }

    companion object {
        const val INPUT_NAME = "Введите ваше имя"
        const val INPUT_LOGIN = "Введите ваш логин"

        @JvmStatic
        fun newInstance() = UserSettingsFragment()
    }
}