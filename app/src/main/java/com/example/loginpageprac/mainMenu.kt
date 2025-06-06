package com.example.loginpageprac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlin.math.max

class mainMenu : AppCompatActivity() {

    private lateinit var pieChart: PieChart
    private lateinit var expBut: Button
    private lateinit var ibtn: Button
    private lateinit var statis: Button
    private lateinit var gBtn: Button
    private lateinit var tBtn: Button
    private val database = Firebase.database
    private val myRef = database.getReference("Goals_Entry")
    private val myRef2 = database.getReference("Expense_Entry")
    private val myRef3 = database.getReference("Income_Entry")
    private val myRef4 = database.getReference("Target_Entry")
    //(Geeks For Geeks, 2025)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        pieChart = findViewById(R.id.pieChart)
        //(Geeks For Geeks, 2025)
        //(see How to create pie chart | MP Android Chart | Android Studio 2024, 2023)
        expBut = findViewById(R.id.expense)
        ibtn = findViewById(R.id.income)
        statis = findViewById(R.id.stats)
        gBtn = findViewById(R.id.goals)
        tBtn = findViewById(R.id.targets)

        //(Geeks For Geeks, 2025)

        setupChart()
        fillchart()
        assignIncome()
        assignExpense()
        assignGoal()
        assignTarget()
        //(Geeks For Geeks, 2025)

        ibtn.setOnClickListener {
            val intent = Intent(this, EntryIncome::class.java)
            startActivity(intent)
        }
        //(Geeks For Geeks, 2025)
        statis.setOnClickListener {
            val intent = Intent(this, Stats::class.java)
            startActivity(intent)
        }
        //(Geeks For Geeks, 2025)
        expBut.setOnClickListener {

            val intent = Intent(this, Entry::class.java)
            startActivity(intent)
        }
        //(Geeks For Geeks, 2025)
        gBtn.setOnClickListener {

            val intent = Intent(this, Goals::class.java)
            startActivity(intent)
        }
        tBtn.setOnClickListener {
            val intent = Intent(this, Target::class.java)
            startActivity(intent)
        }
        //(Geeks For Geeks, 2025)

    }

    private fun fillchart(){
        myRef3.get().addOnSuccessListener { incomeSnapshot ->
            myRef2.get().addOnSuccessListener { expenseSnapshot ->
                val incomeList = incomeSnapshot.children.mapNotNull {
                    it.child("cost").getValue(Float::class.java) ?: 0f
                }
                val expenseList = expenseSnapshot.children.mapNotNull {
                    it.child("cost").getValue(Float::class.java) ?: 0f
                }

                var incTotal = incomeList.sum()
                var expTotal = expenseList.sum()

                try{
                    Storage.newEntry(incTotal, "Income")
                    Storage.newEntry(expTotal, "Expense")
                } catch (e: Exception)
                {
                    Toast.makeText(this, "Error saving data: ${e.message}", Toast.LENGTH_SHORT).show()
                }

                sendIt(incTotal, expTotal)
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to fetch Expenses", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to fetch Income", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendIt(income: Float, expense: Float){
        Storage.entries.clear()
        Storage.entries.add(PieEntry(income, "Income"))
        Storage.entries.add(PieEntry(expense, "Expense"))
        updateChart()
    }


    private fun assignIncome(){

        myRef3.limitToLast(3).get().addOnSuccessListener { dataSnapshot ->
            val getIncomes = dataSnapshot.children.mapNotNull { snap ->
                val incName = snap.child("name").getValue(String::class.java)
                val incCost = snap.child("cost").getValue(Float::class.java)
                if (incName != null && incCost != null) {
                    incName to incCost
                } else null
            }.reversed()

            findViewById<TextView>(R.id.income1).text = getIncomes.getOrNull(0)?.let { "${it.first}: R${it.second}"} ?: "None"
            findViewById<TextView>(R.id.income2).text = getIncomes.getOrNull(1)?.let { "${it.first}: R${it.second}"} ?: "None"
            findViewById<TextView>(R.id.income3).text = getIncomes.getOrNull(2)?.let { "${it.first}: R${it.second}"} ?: "None"

        }.addOnFailureListener {
        }
    }

    private fun assignExpense(){

        myRef2.limitToLast(3).get().addOnSuccessListener { dataSnapshot ->
            val getIncomes = dataSnapshot.children.mapNotNull { snap ->
                val expName = snap.child("name").getValue(String::class.java)
                val expCost = snap.child("cost").getValue(Float::class.java)
                if (expName != null && expCost != null) {
                    expName to expCost
                } else null
            }.reversed()

            findViewById<TextView>(R.id.purch1).text = getIncomes.getOrNull(0)?.let { "${it.first}: R${it.second}"} ?: "None"
            findViewById<TextView>(R.id.purch2).text = getIncomes.getOrNull(1)?.let { "${it.first}: R${it.second}"} ?: "None"
            findViewById<TextView>(R.id.purch3).text = getIncomes.getOrNull(2)?.let { "${it.first}: R${it.second}"} ?: "None"

        }.addOnFailureListener {
        }
    }

    private fun assignGoal(){
        myRef.limitToLast(3).get().addOnSuccessListener { dataSnapshot ->
            val getGoals = dataSnapshot.children.mapNotNull { snap ->
                val goalName = snap.child("month").getValue(Float::class.java)
                val goalMax = snap.child("max_cost").getValue(Float::class.java)
                if (goalName != null && goalMax != null) {
                    goalName to goalMax
                } else null
            }.reversed()

            findViewById<TextView>(R.id.goal1).text = getGoals.getOrNull(0)?.let { "Month:${it.first}: R${it.second}"} ?: "None"
            findViewById<TextView>(R.id.goal2).text = getGoals.getOrNull(1)?.let { "Month:${it.first}: R${it.second}"} ?: "None"
            findViewById<TextView>(R.id.goal3).text = getGoals.getOrNull(2)?.let { "Month:${it.first}: R${it.second}"} ?: "None"

        }.addOnFailureListener {
        }
    }

    private fun assignTarget(){
        myRef4.limitToLast(3).get().addOnSuccessListener { dataSnapshot ->
            val getTargets = dataSnapshot.children.mapNotNull { snap ->
                val tarName = snap.child("name").getValue(String::class.java)
                val tarCost = snap.child("cost").getValue(Float::class.java)
                if (tarName != null && tarCost != null) {
                    tarName to tarCost
                } else null
            }.reversed()

            findViewById<TextView>(R.id.tar1).text = getTargets.getOrNull(0)?.let { "${it.first}: R${it.second}"} ?: "None"
            findViewById<TextView>(R.id.tar2).text = getTargets.getOrNull(1)?.let { "${it.first}: R${it.second}"} ?: "None"
            findViewById<TextView>(R.id.tar3).text = getTargets.getOrNull(2)?.let { "${it.first}: R${it.second}"} ?: "None"

        }.addOnFailureListener {
        }
    }

    private fun setupChart() {
        pieChart.description.isEnabled = false
        pieChart.isDrawHoleEnabled = true
        //(Geeks For Geeks, 2025)
        //(see How to create pie chart | MP Android Chart | Android Studio 2024, 2023)
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(15f)
        //(Geeks For Geeks, 2025)
    }
    private fun updateChart() {
        val dataSet = PieDataSet(Storage.entries, "Data")
        //(Geeks For Geeks, 2025)
        dataSet.setColors(
            intArrayOf(
                android.graphics.Color.RED,
                android.graphics.Color.GREEN,
                android.graphics.Color.BLUE,
                android.graphics.Color.YELLOW,
                android.graphics.Color.MAGENTA
            ), 255
            //(see How to create pie chart | MP Android Chart | Android Studio 2024, 2023)
        )
        //(Geeks For Geeks, 2025)
        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.invalidate()
        //(Geeks For Geeks, 2025)
    }
}