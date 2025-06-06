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
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Stats : AppCompatActivity() {

    lateinit var month1 : EditText
    lateinit var month2 : EditText
    lateinit var pBtn : Button
    val database = Firebase.database
    val incRef = database.getReference("Income_Entry")
    val expRef = database.getReference("Expense_Entry")
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
                    gatherData(start, end) { result ->
                        findViewById<TextView>(R.id.grid).text = result
                    }
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

    private fun gatherData(first: Float, second: Float, callback: (String) -> Unit){
        val collect = StringBuilder()
        val incomeList = mutableListOf<String>()
        val expenseList = mutableListOf<String>()
        //(Kotlin, 2025)
        /*
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

         */

        incRef.get().addOnSuccessListener { incomeSnapshot ->
            for (inc in incomeSnapshot.children)
            {
                val month = inc.child("month").getValue(Float::class.java) ?: continue

                if (month >= first && month <= second)
                {
                    val name = inc.child("name").getValue(String::class.java) ?: ""
                    val price = inc.child("cost").getValue(Float::class.java) ?: 0f
                    val day = inc.child("day").getValue(Float::class.java) ?: 0f
                    val year = inc.child("year").getValue(Float::class.java) ?: 0f
                    val desc = inc.child("description").getValue(String::class.java) ?: ""
                    val cate = inc.child("category").getValue(String::class.java) ?: ""

                    incomeList.add("""
                        Name: $name
                        Price: R$price
                        Date: $day-$month-$year
                        Description: $desc
                        Category: $cate
                    """.trimIndent())
                }
            }
            expRef.get().addOnSuccessListener { expenseSnapshot ->
                for (exp in expenseSnapshot.children)
                {
                    val month = exp.child("month").getValue(Float::class.java) ?: continue

                    if (month >= first && month <= second)
                    {
                        val name = exp.child("name").getValue(String::class.java) ?: ""
                        val price = exp.child("cost").getValue(Float::class.java) ?: 0f
                        val day = exp.child("day").getValue(Float::class.java) ?: 0f
                        val year = exp.child("year").getValue(Float::class.java) ?: 0f
                        val desc = exp.child("description").getValue(String::class.java) ?: ""
                        val cate = exp.child("category").getValue(String::class.java) ?: ""

                        expenseList.add("""
                        Name: $name
                        Price: R$price
                        Date: $day-$month-$year
                        Description: $desc
                        Category: $cate
                    """.trimIndent())
                    }
                }

                collect.append("Income\n\n")
                incomeList.forEach { collect.append(it).append("\n\n") }

                collect.append("Expenses\n\n")
                expenseList.forEach { collect.append(it).append("\n") }

                callback(collect.toString())
            }.addOnFailureListener {
                collect.append("Income\n\n")
                incomeList.forEach { collect.append(it).append("\n") }
                collect.append("Expenses\n\nFailed to load expenses")
                callback(collect.toString())
            }
        }.addOnFailureListener {
            callback("Failed to load income data.")
        }
    }






}