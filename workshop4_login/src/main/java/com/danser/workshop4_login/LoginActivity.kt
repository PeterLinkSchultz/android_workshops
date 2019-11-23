package com.danser.workshop4_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

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
            register()
        }
    }

    private fun login(login: String, password: String) {
        if (login.isBlank()) {

            return
        }
        if (login != DEFAULT_LOGIN || password != DEFAULT_PASSWORD) {

            return
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun register() {

    }

    companion object {
        private const val DEFAULT_LOGIN = "q"
        private const val DEFAULT_PASSWORD = "q"
    }
}
