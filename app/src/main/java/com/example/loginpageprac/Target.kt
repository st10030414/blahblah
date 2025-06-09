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
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Target : AppCompatActivity() {
    private lateinit var targetName: EditText
    private lateinit var targetPrice: EditText
    private lateinit var tarBtn: Button
    private val database = Firebase.database
    private val myRef = database.getReference("Target_Entry")
    lateinit var backBtn : Button
    //(Geeks For Geeks, 2025)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)

        targetName = findViewById(R.id.tarName)
        targetPrice = findViewById(R.id.tarPrice)
        tarBtn = findViewById(R.id.addTar)
        backBtn = findViewById(R.id.back)
        //(Geeks For Geeks, 2025)
        //(Android, 2025)

        tarBtn.setOnClickListener{
            val tName = targetName.text.toString()
            val tPrice = targetPrice.text.toString().toFloatOrNull()
            //(Geeks For Geeks, 2025)

            if (tName.isEmpty() || tPrice == null)
            {
                Toast.makeText(this, "Invalid or empty inputs detected!", Toast.LENGTH_SHORT).show()
            }
            //(W3Schools, 2025)
            else
            {
                val newExp = mapOf(
                    "name" to tName,
                    "cost" to tPrice
                )
                myRef.push().setValue(newExp).addOnSuccessListener {
                    Toast.makeText(this, "User details successfully entered!", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to enter user details", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, mainMenu::class.java)
                startActivity(intent)
            }
            //(W3Schools, 2025)
        }

        backBtn.setOnClickListener {
            val intent = Intent(this, mainMenu::class.java)
            startActivity(intent)
        }

    }
}