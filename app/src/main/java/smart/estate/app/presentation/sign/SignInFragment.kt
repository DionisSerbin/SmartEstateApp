package smart.estate.app.presentation.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import smart.estate.app.R
import smart.estate.app.presentation.MainActivity


class SignInFragment : Fragment() {

    interface FragmentCreation {
        fun createSignUpFragment()
    }


    override fun onStart() {
        super.onStart()
        if ((activity as SignInActivity).getFirebaseAuthInstance().currentUser != null) {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notReg = view.findViewById<TextView>(R.id.not_reg)
        notReg.setOnClickListener {
            (activity as FragmentCreation).createSignUpFragment()
        }

        val signInButton = view.findViewById<MaterialButton>(R.id.sign_in_button)
        signInButton.setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.email_text).text.toString()
            val pass = view.findViewById<TextInputEditText>(R.id.password_text).text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                (activity as SignInActivity).getFirebaseAuthInstance()
                    .signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(signInButton.context, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            signInButton.context,
                            it.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Firebase", it.exception.toString())
                    }
                }
            } else {
                Toast.makeText(signInButton.context, WRONG_DATA, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        private const val WRONG_DATA = "Вы не ввели все данные"

        @JvmStatic
        fun newInstance() = SignInFragment()
    }
}