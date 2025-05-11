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
import kotlin.math.max

class mainMenu : AppCompatActivity() {

    private lateinit var pieChart: PieChart
    private lateinit var expBut: Button
    private lateinit var pbtn: Button
    private lateinit var ibtn: Button
    private lateinit var statis: Button
    private lateinit var gBtn: Button
    //(Geeks For Geeks, 2025)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        pieChart = findViewById(R.id.pieChart)
        //(Geeks For Geeks, 2025)
        //(see How to create pie chart | MP Android Chart | Android Studio 2024, 2023)
        expBut = findViewById(R.id.expense)
        pbtn = findViewById(R.id.per)
        ibtn = findViewById(R.id.income)
        statis = findViewById(R.id.stats)
        gBtn = findViewById(R.id.goals)
        //(Geeks For Geeks, 2025)

        setupChart()
        updateChart()
        assignIncome()
        assignExpense()
        assignGoal()
        //(Geeks For Geeks, 2025)

        pbtn.setOnClickListener {
            popop()
        }
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
        //(Geeks For Geeks, 2025)

    }

    private fun fillchart(){
        val totalIncome = Storage.income.sumOf { it.price.toDouble() }.toFloat()
        val totalExpense = Storage.expense.sumOf { it.price.toDouble() }.toFloat()
        //(Geeks For Geeks, 2025)
        Storage.newEntry(totalIncome, "Income")
        Storage.newEntry(totalExpense, "Expenses")
        //(Geeks For Geeks, 2025)
    }

    private fun fillIncome(){
        Storage.income.add(Income(700f , "Interest on Positive Balance", "25", "04", "2025", "Bank interest", "Food"))
        Storage.income.add(Income(500f , "Beer Fund", "12", "06", "2025", "Refund", "Food"))
        Storage.income.add(Income(4000f , "Wages", "05", "07", "2025", "Paid to work construction", "Food"))
        Storage.income.add(Income(21000f , "Salary", "15", "03", "2025", "Paid to work Surgery", "Food"))
        //(Kotlin, 2025)

    }

    private fun fillExpense(){
        Storage.expense.add(Expense("Groceries", 2000.00f, "22", "05", "2025", "Need to eat", "Food"))
        Storage.expense.add(Expense("Clothing", 2000.00f, "02", "07", "2025", "Need to wear", "Clothing"))
        Storage.expense.add(Expense("School Fees", 8000.00f, "21", "01", "2025", "Need to learn", "Schooling"))
        Storage.expense.add(Expense("Rent", 4000.00f, "05", "11", "2025", "Need to sleep", "Housing"))
        //(Kotlin, 2025)

    }

    private fun fillGoals(){
        Storage.goal.add(GoalHolder("Vacation to Miami", 2000f, 1000f))
        Storage.goal.add(GoalHolder("Vacation to Tahiti", 4000f, 5000f))
        Storage.goal.add(GoalHolder("Save for GTA6",8000f, 400f))
        Storage.goal.add(GoalHolder("Fix Lawn Mower", 400f, 150f))
        //(Kotlin, 2025)
    }

    private fun assignIncome(){
        val lThree = Storage.income.takeLast(3).map {it.price}
        val nameThree = Storage.income.takeLast(3).map {it.name}
        //(Kotlin, 2025)


        findViewById<TextView>(R.id.income1).text = (nameThree.getOrNull(0)?.toString() ?: "None") + ": R" + (lThree.getOrNull(0)?.toString() ?: "None")
        findViewById<TextView>(R.id.income2).text = (nameThree.getOrNull(1)?.toString() ?: "None") + ": R" + (lThree.getOrNull(1)?.toString() ?: "None")
        findViewById<TextView>(R.id.income3).text = (nameThree.getOrNull(2)?.toString() ?: "None") + ": R" + (lThree.getOrNull(2)?.toString() ?: "None")
        //(Kotlin, 2025)
    }

    private fun assignExpense(){

        val lThree = Storage.expense.takeLast(3).map {it.price}
        val lastThree = Storage.expense.takeLast(3).map {it.name}
        //(Kotlin, 2025)

        findViewById<TextView>(R.id.purch1).text = (lastThree.getOrNull(0)?.toString() ?: "None") + ": R" + (lThree.getOrNull(0)?.toString() ?: "None")
        findViewById<TextView>(R.id.purch2).text = (lastThree.getOrNull(1)?.toString() ?: "None") + ": R" + (lThree.getOrNull(1)?.toString() ?: "None")
        findViewById<TextView>(R.id.purch3).text = (lastThree.getOrNull(2)?.toString() ?: "None") + ": R" + (lThree.getOrNull(2)?.toString() ?: "None")
        //(Kotlin, 2025)
    }

    private fun assignGoal(){
        val nameThree = Storage.goal.takeLast(3).map {it.goalName}
        val maxThree = Storage.goal.takeLast(3).map {it.maxGoal}
        val minThree = Storage.goal.takeLast(3).map {it.minGoal}
        //(Kotlin, 2025)

        findViewById<TextView>(R.id.goal1).text =
            (nameThree.getOrNull(0)?.toString() ?: "None") + ": R" +
                    (minThree.getOrNull(0)?.toString() ?: "None") + " - R" +
                    (maxThree.getOrNull(0)?.toString() ?: "None")
        //(Geeks For Geeks, 2025)

        findViewById<TextView>(R.id.goal2).text =
            (nameThree.getOrNull(1)?.toString() ?: "None") + ": R" +
                    (minThree.getOrNull(1)?.toString() ?: "None") + " - R" +
                    (maxThree.getOrNull(1)?.toString() ?: "None")
        //(Geeks For Geeks, 2025)

        findViewById<TextView>(R.id.goal3).text =
            (nameThree.getOrNull(2)?.toString() ?: "None") + ": R" +
                    (minThree.getOrNull(2)?.toString() ?: "None") + " - R" +
                    (maxThree.getOrNull(2)?.toString() ?: "None")
        //(Kotlin, 2025)
    }

    private fun popop(){
        fillIncome()
        fillExpense()
        fillGoals()
        fillchart()
        setupChart()
        updateChart()
        assignIncome()
        assignExpense()
        assignGoal()
        //(Geeks For Geeks, 2025)
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