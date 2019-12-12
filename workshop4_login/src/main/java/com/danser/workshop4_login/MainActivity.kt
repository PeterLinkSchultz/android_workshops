package com.danser.workshop4_login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val prefs by lazy {
        getSharedPreferences("com.danser.workshop4_login", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setLogoutListener()
        setUserName();
    }

    private fun setLogoutListener() {
        logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setUserName() {
        userName.text = prefs.getString(LAST_LOGIN_INPUT_KEY, "")
    }

    companion object {
        private const val LAST_LOGIN_INPUT_KEY = "LAST_LOGIN_INPUT"
    }
}
