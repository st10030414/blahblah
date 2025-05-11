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

class Goals : AppCompatActivity() {
    private lateinit var goalName: EditText
    private lateinit var goalMax: EditText
    private lateinit var goalMin: EditText
    private lateinit var goalBtn: Button
    //(Geeks For Geeks, 2025)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        goalName = findViewById(R.id.goalName)
        goalMax = findViewById(R.id.goalMax)
        goalMin = findViewById(R.id.goalMin)
        goalBtn = findViewById(R.id.addGoal)
        //(Geeks For Geeks, 2025)
        //(Android, 2025)

        goalBtn.setOnClickListener{
            val gName = goalName.text.toString()
            val gMax = goalMax.text.toString().toFloat()
            val gMin = goalMin.text.toString().toFloat()
            //(Geeks For Geeks, 2025)

            if (gName.isBlank())
            {
                Toast.makeText(this, "Empty inputs detected!", Toast.LENGTH_SHORT).show()
            }
            //(W3Schools, 2025)
            else
            {
                Storage.goal.add(GoalHolder(gName, gMax, gMin))
                val intent = Intent(this, mainMenu::class.java)
                startActivity(intent)
                //(Geeks For Geeks, 2025)
            }
            //(W3Schools, 2025)
        }

    }
}