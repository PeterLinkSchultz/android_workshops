package com.danser.workshop4_login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindViews()
    }

    private fun bindViews() {
        bLogin.setOnClickListener {
            login(
                login = etLogin.text.toString(),
                password = etPassword.text.toString()
            )
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

    companion object {
        private const val DEFAULT_LOGIN = "q"
        private const val DEFAULT_PASSWORD = "q"
    }
}
