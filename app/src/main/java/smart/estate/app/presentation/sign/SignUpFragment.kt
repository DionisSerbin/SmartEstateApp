package smart.estate.app.presentation.sign

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import smart.estate.app.R


class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val alreadyReg = view.findViewById<TextView>(R.id.already_reg)
        alreadyReg.setOnClickListener {
//            val intent = Intent(alreadyReg.context, SignInActivity::class.java)
//            startActivity(intent)
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
                    (activity as SignInActivity).getFirebaseAuthInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
//                            val intent = Intent(signUpButton.context, SignInActivity::class.java)
//                            startActivity(intent)
                            (activity as EntryFragment.FragmentCreation).createSignInFragment()
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