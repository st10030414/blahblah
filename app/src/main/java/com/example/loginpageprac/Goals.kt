package com.example.loginpageprac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Goals : AppCompatActivity() {
    private lateinit var goalMonth: EditText
    private lateinit var goalMax: EditText
    private lateinit var goalMin: EditText
    private lateinit var goalBtn: Button
    private val database = Firebase.database
    private val myRef = database.getReference("Goals_Entry")
    lateinit var backBtn : Button
    //(Geeks For Geeks, 2025)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        goalMonth = findViewById(R.id.goalMonth)
        goalMax = findViewById(R.id.goalMax)
        goalMin = findViewById(R.id.goalMin)
        goalBtn = findViewById(R.id.addGoal)
        backBtn = findViewById(R.id.back)
        //(Geeks For Geeks, 2025)
        //(Android, 2025)

        goalBtn.setOnClickListener{
            val gMonth = goalMonth.text.toString().toIntOrNull()
            val gMax = goalMax.text.toString().toFloatOrNull()
            val gMin = goalMin.text.toString().toFloatOrNull()
            //(Geeks For Geeks, 2025)

            if (gMonth == null || gMax == null || gMin == null || gMax < gMin)
            {
                Toast.makeText(this, "Invalid or empty inputs detected!", Toast.LENGTH_SHORT).show()
            }
            //(W3Schools, 2025)
            else
            {

                val newExp = mapOf(
                    "month" to gMonth,
                    "max_cost" to gMax,
                    "min_cost" to gMin
                )
                myRef.push().setValue(newExp).addOnSuccessListener {
                    Toast.makeText(this, "User details successfully entered!", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "User details successfully entered!", Toast.LENGTH_SHORT).show()
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