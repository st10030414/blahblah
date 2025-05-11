package com.example.loginpageprac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var userInput : EditText
    lateinit var passInput : EditText
    lateinit var logPush : Button
    //(Kotlin, 2025)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userInput = findViewById(R.id.user)
        passInput = findViewById(R.id.pass)
        logPush = findViewById(R.id.login_btn)
        //(Android, 2025)

        logPush.setOnClickListener{

            val username = "Admin"
            val password = "1234"
            if (this.userInput.text.toString().contains(username) && this.passInput.text.toString().contains(password))
            {
                val intent = Intent(this, mainMenu::class.java)
                startActivity(intent)
                //(Android, 2025)
            }
            //(W3Schools, 2025)
            else
            {
                Toast.makeText(this, "Invalid input detected!\nUsername = Admin\nPassword = 1234", Toast.LENGTH_SHORT).show()
                //(Kotlin, 2025)
            }
            //(W3Schools, 2025)
        }
    }
}