package com.example.loginpageprac

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Stats : AppCompatActivity() {

    private lateinit var month1 : EditText
    private lateinit var month2 : EditText
    private lateinit var pBtn : Button
    private lateinit var allBtn : Button
    lateinit var backBtn : Button
    private lateinit var progBar : ProgressBar
    val database = Firebase.database
    val incRef = database.getReference("Income_Entry")
    val expRef = database.getReference("Expense_Entry")
    val goalRef = database.getReference("Goals_Entry")
    val tarRef = database.getReference("Target_Entry")
    //(Android, 2025)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        month1 = findViewById(R.id.month1)
        month2 = findViewById(R.id.month2)
        pBtn = findViewById(R.id.addPeriod)
        allBtn = findViewById(R.id.addAll)
        backBtn = findViewById(R.id.back)
        progBar = findViewById(R.id.progressBar)
        //(Android, 2025)
        pBtn.setOnClickListener {
            try {
                val start = month1.text.toString().toFloat()
                val end = month2.text.toString().toFloat()
                if (start > 0 && start < 13 && end > 0 && end < 13 && start <= end)
                {
                    findViewById<TextView>(R.id.grid).text = "Statistics"
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

        allBtn.setOnClickListener {
            findViewById<TextView>(R.id.grid).text = "Statistics"
            showAll { result ->
                findViewById<TextView>(R.id.grid).text = result
            }
        }

        backBtn.setOnClickListener {
            val intent = Intent(this, mainMenu::class.java)
            startActivity(intent)
        }
    }

    private fun gatherData(first: Float, second: Float, callback: (String) -> Unit){
        progBar.visibility = View.VISIBLE
        val collect = StringBuilder()
        val incomeList = mutableListOf<String>()
        val expenseList = mutableListOf<String>()
        //(Kotlin, 2025)


        incRef.get().addOnSuccessListener { incomeSnapshot ->
            for (inc in incomeSnapshot.children)
            {
                val month = inc.child("month").getValue(Int::class.java) ?: continue

                if (month >= first && month <= second)
                {
                    val name = inc.child("name").getValue(String::class.java) ?: ""
                    val price = inc.child("cost").getValue(Float::class.java) ?: 0f
                    val day = inc.child("day").getValue(Int::class.java) ?: 0
                    val year = inc.child("year").getValue(Int::class.java) ?: 0
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
                    val month = exp.child("month").getValue(Int::class.java) ?: continue

                    if (month >= first && month <= second)
                    {
                        val name = exp.child("name").getValue(String::class.java) ?: ""
                        val price = exp.child("cost").getValue(Float::class.java) ?: 0f
                        val day = exp.child("day").getValue(Int::class.java) ?: 0f
                        val year = exp.child("year").getValue(Int::class.java) ?: 0f
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
                progBar.visibility = View.GONE
                callback(collect.toString())
            }.addOnFailureListener {
                collect.append("Income\n\n")
                incomeList.forEach { collect.append(it).append("\n") }
                collect.append("Expenses\n\nFailed to load expenses")
                progBar.visibility = View.GONE
                callback(collect.toString())
            }
        }.addOnFailureListener {
            progBar.visibility = View.GONE
            callback("Failed to load income data.")
        }
    }

    private fun showAll( callback: (String) -> Unit){
        progBar.visibility = View.VISIBLE
        val collect = StringBuilder()
        val incomeList = mutableListOf<String>()
        val expenseList = mutableListOf<String>()
        val goalsList = mutableListOf<String>()
        val targetList = mutableListOf<String>()
        //(Kotlin, 2025)

        incRef.get().addOnSuccessListener { incomeSnapshot ->
            for (inc in incomeSnapshot.children)
            {
                val name = inc.child("name").getValue(String::class.java) ?: ""
                val price = inc.child("cost").getValue(Float::class.java) ?: 0f
                val day = inc.child("day").getValue(Int::class.java) ?: 0
                val month = inc.child("month").getValue(Int::class.java) ?: 0
                val year = inc.child("year").getValue(Int::class.java) ?: 0
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
            expRef.get().addOnSuccessListener { expenseSnapshot ->
                for (exp in expenseSnapshot.children)
                {
                    val name = exp.child("name").getValue(String::class.java) ?: ""
                    val price = exp.child("cost").getValue(Float::class.java) ?: 0f
                    val day = exp.child("day").getValue(Int::class.java) ?: 0
                    val month = exp.child("month").getValue(Int::class.java) ?: 0
                    val year = exp.child("year").getValue(Int::class.java) ?: 0
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
                goalRef.get().addOnSuccessListener { goalSnapshot ->
                    for (goal in goalSnapshot.children)
                    {
                        val month = goal.child("month").getValue(Int::class.java) ?: 0
                        val min = goal.child("min_cost").getValue(Float::class.java) ?: 0f
                        val max = goal.child("max_cost").getValue(Float::class.java) ?: 0f

                        goalsList.add("""
                        month: $month
                        min_cost: R$min
                        max_cost: R$max
                    """.trimIndent())
                    }
                    tarRef.get().addOnSuccessListener { targetSnapshot ->
                        for (target in targetSnapshot.children)
                        {
                            val name = target.child("name").getValue(String::class.java) ?: ""
                            val price = target.child("cost").getValue(Float::class.java) ?: 0f

                            targetList.add("""
                                name: $name
                                cost: R$price
                            """.trimIndent()

                            )
                        }
                        collect.append("Income\n\n").append(incomeList.joinToString("\n\n"))
                        collect.append("\n\nExpenses\n\n").append(expenseList.joinToString("\n\n"))
                        collect.append("\n\nGoals\n\n").append(goalsList.joinToString("\n\n"))
                        collect.append("\n\nTargets\n\n").append(targetList.joinToString("\n\n"))
                        progBar.visibility = View.GONE
                        callback(collect.toString())
                    }
                }
            }
        }.addOnFailureListener {
            callback("Failure")
        }


    }

}