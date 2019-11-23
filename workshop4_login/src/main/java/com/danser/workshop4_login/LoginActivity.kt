package com.danser.workshop4_login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val prefs by lazy {
        getSharedPreferences("com.danser.workshop4_login", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindViews()
    }

    private fun bindViews() {
        setLoginState()
        swRegister.setOnCheckedChangeListener { _, checked ->
            when {
                checked -> setRegisterState()
                else -> setLoginState()
            }
        }
    }

    private fun setLoginState() {
        bLogin.text = "Login"
        etRepeatPassword.visibility = View.GONE
        bLogin.setOnClickListener {
            login(
                login = etLogin.text.toString(),
                password = etPassword.text.toString()
            )
        }
    }

    private fun setRegisterState() {
        bLogin.text = "Register"
        etRepeatPassword.visibility = View.VISIBLE
        bLogin.setOnClickListener {
            register(
                login = etLogin.text.toString(),
                password = etPassword.text.toString(),
                repeatedPassword = etRepeatPassword.toString()
            )
        }
    }

    private fun login(login: String, password: String) {
        val correctLogin = prefs.getString(LOGIN_KEY, "")
        val correctPassword = prefs.getString(PASSWORD_KEY, "")
        if (login.isBlank()) {
            showSnack("Couldn't login with empty login")
            return
        }
        if (login != correctLogin || password != correctPassword) {
            showSnack("Login or password are incorrect")
            return
        }
        startMainScreenAndClose()
        showSnack("Logged in")
    }

    private fun register(login: String, password: String, repeatedPassword: String) {
        if (login.isBlank()) {
            showSnack("Couldn't register with empty login")
            return
        }
        if (password.isBlank()) {
            showSnack("Couldn't register with empty password")
            return
        }
        if (password != repeatedPassword) {
            showSnack("Couldn't register, passwords should be the same")
            return
        }
        prefs.edit().putString(LOGIN_KEY, login).apply()
        prefs.edit().putString(PASSWORD_KEY, password).apply()
        startMainScreenAndClose()
        showSnack("Registered")
    }

    private fun showSnack(text: String) {
        Snackbar.make(vRoot, text, Snackbar.LENGTH_SHORT).show()
    }

    private fun startMainScreenAndClose() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val LOGIN_KEY = "login"
        private const val PASSWORD_KEY = "password"
    }
}
