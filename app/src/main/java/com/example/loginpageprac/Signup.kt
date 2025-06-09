package com.example.loginpageprac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginpageprac.MainActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Signup : AppCompatActivity() {
    lateinit var userInput : EditText
    lateinit var passInput : EditText
    lateinit var signPush : Button
    lateinit var backBtn : Button
    private val database = Firebase.database
    private val myRef = database.getReference("Credentials")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        userInput = findViewById(R.id.user)
        passInput = findViewById(R.id.pass)
        signPush = findViewById(R.id.Signup_btn)
        backBtn = findViewById(R.id.back)



        signPush.setOnClickListener {
            val newUser = userInput.text.toString()
            val newPass = passInput.text.toString()

            if (newUser.isBlank() || newPass.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newCred = mapOf(
                "username" to newUser,
                "password" to newPass
            )
            myRef.push().setValue(newCred).addOnSuccessListener {
                Toast.makeText(this, "User details successfully entered!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "User details successfully entered!", Toast.LENGTH_SHORT).show()
            }
        }

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}