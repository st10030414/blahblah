package com.example.loginpageprac

import android.app.DatePickerDialog
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
import java.util.Calendar

class EntryIncome : AppCompatActivity() {
    private lateinit var incomeName: EditText
    private lateinit var incomeCost: EditText
    private lateinit var incomeDay: EditText
    private lateinit var incomeMonth: EditText
    private lateinit var incomeYear: EditText
    private lateinit var incomeDesc: EditText
    private lateinit var incomeCate: EditText
    private lateinit var incButton: Button
    lateinit var backBtn : Button
    private val database = Firebase.database
    private val myRef = database.getReference("Income_Entry")
    //(Geeks FOr Geeks, 2025)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_income)
        incomeName = findViewById(R.id.incName)
        incomeCost = findViewById(R.id.incCost)
        incomeDay = findViewById(R.id.incDay)
        incomeMonth = findViewById(R.id.incMonth)
        incomeYear = findViewById(R.id.incYear)
        incomeDesc = findViewById(R.id.incDesc)
        incomeCate = findViewById(R.id.incCate)
        incButton = findViewById(R.id.addIncome)
        backBtn = findViewById(R.id.back)
        //(Geeks FOr Geeks, 2025)

        incButton.setOnClickListener {
            try {
                val iName = incomeName.text.toString()
                val iCost = incomeCost.text.toString().toFloatOrNull()
                val iDay= incomeDay.text.toString().toIntOrNull()
                val iMonth= incomeMonth.text.toString().toIntOrNull()
                //(Android, 2025)
                val iYear= incomeYear.text.toString().toIntOrNull()
                val iDesc= incomeDesc.text.toString()
                val iCate= incomeCate.text.toString()
                //(Geeks FOr Geeks, 2025)

                if(iName.isBlank() || iDay == null ||
                    iMonth == null || iYear == null ||
                    iDesc.isBlank() || iCate.isBlank() || iCost == null)
                {
                    Toast.makeText(this, "Empty inputs detected!", Toast.LENGTH_SHORT).show()
                }
                else
                //(W3Schools, 2025)
                {
                    /*
                    Storage.income.add(Income(name = iName, price = iCost, day = iDay, month = iMonth, year = iYear, description = iDesc, category = iCate))
                    //(Android, 2025)
                    val intent = Intent(this, mainMenu::class.java)
                    startActivity(intent)
                    //(Geeks FOr Geeks, 2025)
                     */

                    val newExp = mapOf(
                        "name" to iName,
                        "cost" to iCost,
                        "day" to iDay,
                        "month" to iMonth,
                        "year" to iYear,
                        "description" to iDesc,
                        "category" to iCate
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
            catch (e: Exception)
            {
                Toast.makeText(this, "Invalid input detected!", Toast.LENGTH_SHORT).show()
                //(Kotlin, 2025)
                //(Android, 2025)
            }
        }

        backBtn.setOnClickListener {
            val intent = Intent(this, mainMenu::class.java)
            startActivity(intent)
        }

    }
}
