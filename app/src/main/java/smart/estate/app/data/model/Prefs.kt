package smart.estate.app.data.model

import android.content.Context
import android.content.SharedPreferences
import kotlin.math.log

class Prefs (context: Context) {
    private val PREFS_NAME = "sharedpref"
    private var sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun putMail(mail: String) {
        editor.putString(MAIL_KEY, mail).apply()
    }

    fun putLogin(login: String) {
        editor.putString(LOGIN_KEY, login).apply()
    }

    fun putName(name: String) {
        editor.putString(NAME_KEY, name).apply()
    }

    fun getMail(): String? {
        return sharedPref.getString(MAIL_KEY, null)
    }

    fun getLogin(): String? {
        return sharedPref.getString(LOGIN_KEY, null)
    }

    fun getName(): String? {
        return sharedPref.getString(NAME_KEY, null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }

    companion object {
        const val MAIL_KEY = "MAIL_KEY"
        const val LOGIN_KEY = "LOGIN_KEY"
        const val NAME_KEY = "NAME_KEY"
    }
}