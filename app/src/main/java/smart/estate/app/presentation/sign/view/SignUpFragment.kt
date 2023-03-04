package smart.estate.app.presentation.sign.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import smart.estate.app.R
import smart.estate.app.data.model.Prefs
import smart.estate.app.data.model.request.User
import smart.estate.app.presentation.sign.viewmodel.SignViewModel
import smart.estate.app.presentation.smart.search.viewmodel.SaveSmartSearchViewModel

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val signViewModel: SignViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var alredyRegister = false

        val alreadyReg = view.findViewById<TextView>(R.id.already_reg)
        alreadyReg.setOnClickListener {
            (activity as EntryFragment.FragmentCreation).createSignInFragment()
        }

        val signUpButton = view.findViewById<Button>(R.id.sign_up_button)

        signUpButton.setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.email_text).text.toString()
            val pass = view.findViewById<TextInputEditText>(R.id.password_text).text.toString()
            val confirmPass =
                view.findViewById<TextInputEditText>(R.id.confirm_password_text).text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    (activity as SignInActivity).getFirebaseAuthInstance()
                        .createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful) {
                                viewLifecycleOwner.lifecycleScope.launch {
                                    if (!alredyRegister) {

                                        val responseSuccess = withContext(Dispatchers.IO) {
                                            async {
                                                signViewModel.createUser(User(email))
                                            }
                                        }.await()
                                        if (responseSuccess == null) {
                                            Toast.makeText(
                                                context,
                                                "Возникла ошибка",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            alredyRegister = true
                                            (activity as SignInActivity).prefs.putMail(email)
                                            (activity as EntryFragment.FragmentCreation).createSignInFragment()
                                        }
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    signUpButton.context,
                                    it.exception.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(signUpButton.context, WRONG_PASSWORD_REPEAT, Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(signUpButton.context, WRONG_DATA, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {

        private const val WRONG_DATA = "Вы не ввели все данные"
        private const val WRONG_PASSWORD_REPEAT = "Пароли не совпадают"

        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}