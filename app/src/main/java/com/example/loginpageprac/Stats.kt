package com.example.loginpageprac

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast

class Stats : AppCompatActivity() {

    lateinit var month1 : EditText
    lateinit var month2 : EditText
    lateinit var pBtn : Button
    //(Android, 2025)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        month1 = findViewById(R.id.month1)
        month2 = findViewById(R.id.month2)
        pBtn = findViewById(R.id.addPeriod)
        //(Android, 2025)
        pBtn.setOnClickListener {
            try {
                val start = month1.text.toString().toFloat()
                val end = month2.text.toString().toFloat()
                if (start > 0 && start < 13 && end > 0 && end < 13 && start <= end)
                {
                    findViewById<TextView>(R.id.grid).text = gatherData(start, end)
                }
                else
                {
                    Toast.makeText(this, "Invalid input detected!", Toast.LENGTH_SHORT).show()
                }

            } catch(e: Exception)
            {
                Toast.makeText(this, "Invalid input detected!", Toast.LENGTH_SHORT).show()
            }


            //(Android, 2025)
        }
    }

    private fun gatherData(first: Float, second: Float) : String{
        val collect = StringBuilder()
        //(Kotlin, 2025)
        collect.append("Income\n\n")


        for (incomeStat in Storage.income)
        {
            if (incomeStat.month.toFloat() >= first && incomeStat.month.toFloat() <= second) {
                collect.append("Name: ${incomeStat.name}\n")
                collect.append("Price: R${incomeStat.price}\n")
                collect.append("Date: ${incomeStat.day}-${incomeStat.month}-${incomeStat.year}\n")
                collect.append("Description: ${incomeStat.description}\n")
                collect.append("Category: ${incomeStat.category}\n\n")
                //(Kotlin, 2025)
                //(W3Schools, 2025)
            }

            //(W3Schools, 2025)

        }

        collect.append("Expenses\n\n")

        for (expenseStat in Storage.expense)
        {
            if(expenseStat.month.toFloat() >= first && expenseStat.month.toFloat() <= second) {
                collect.append("Name: ${expenseStat.name}\n")
                collect.append("Price: ${expenseStat.price}\n")
                collect.append("Date: ${expenseStat.day}-${expenseStat.month}-${expenseStat.year}\n")
                collect.append("Description: ${expenseStat.description}\n")
                collect.append("Category: ${expenseStat.category}\n\n")
                //(Kotlin, 2025)
                //(W3Schools, 2025)
            }
            //(W3Schools, 2025)
        }
        return collect.toString()

    }






}