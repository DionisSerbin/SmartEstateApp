package smart.estate.app.presentation.user.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import smart.estate.app.R
import smart.estate.app.data.model.Prefs
import smart.estate.app.data.model.request.UpdatedUser
import smart.estate.app.data.model.request.User
import smart.estate.app.data.model.response.UserSettings
import smart.estate.app.data.model_processing.Validator
import smart.estate.app.presentation.sign.viewmodel.SignViewModel
import smart.estate.app.presentation.user.viewmodel.UserViewModel

class UserSettingsFragment : Fragment() {

    private lateinit var prefs: Prefs

    private val signViewModel: UserViewModel by activityViewModels()

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
                viewLifecycleOwner.lifecycleScope.launch {
                    val responseSuccess = withContext(Dispatchers.IO) {
                        async {
                            signViewModel.updateUser(
                                UpdatedUser(
                                    userLogin = loginInputView.text.toString(),
                                    userName = nameInputView.text.toString(),
                                    userMail = prefs.getMail()!!
                                )
                            )
                        }
                    }.await()
                    if (responseSuccess == null) {
                        Toast.makeText(
                            context,
                            "Возникла ошибка",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        prefs.putName(nameInputView.text.toString())
                        prefs.putLogin(loginInputView.text.toString())
                        findNavController().navigate(R.id.action_userSettingsFragment_to_navigation_user)
                    }
                }
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