package com.example.loginpageprac

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Signup : AppCompatActivity() {
    lateinit var userInput : EditText
    lateinit var passInput : EditText
    lateinit var signPush : Button
    private val database = Firebase.database
    private val myRef = database.getReference("Credentials")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        userInput = findViewById(R.id.user)
        passInput = findViewById(R.id.pass)
        signPush = findViewById(R.id.Signup_btn)



        signPush.setOnClickListener {
            val newUser = userInput.text.toString()
            val newPass = passInput.text.toString()

            val newCred = mapOf(
                "username" to newUser,
                "password" to newPass
            )
            myRef.push().setValue(newCred).addOnSuccessListener {
                Toast.makeText(this, "User details successfully entered!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "User details successfully entered!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}