package com.example.loginpageprac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//(Geeks For Geeks, 2025)

class Entry : AppCompatActivity() {


    private lateinit var expenseName: EditText
    private lateinit var expenseCost: EditText
    private lateinit var expenseDay: EditText
    private lateinit var expenseMonth: EditText
    private lateinit var expenseYear: EditText
    private lateinit var expenseDesc: EditText
    private lateinit var expenseCate: EditText
    //(Geeks For Geeks, 2025)

    private lateinit var expButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)


        expenseName = findViewById(R.id.expName)
        expenseCost = findViewById(R.id.expCost)
        expenseDay = findViewById(R.id.expDay)
        expenseMonth = findViewById(R.id.expMonth)
        expenseYear = findViewById(R.id.expYear)
        expenseDesc = findViewById(R.id.expDesc)
        expenseCate = findViewById(R.id.expCate)
        //(Geeks For Geeks, 2025)

        expButton = findViewById(R.id.addExpense)
        //(Geeks For Geeks, 2025)


        expButton.setOnClickListener {
            try
            {
                val  eName= expenseName.text.toString()
                val eCost = expenseCost.text.toString().toFloat()
                val  eDate= expenseDay.text.toString()
                val  eStart= expenseMonth.text.toString()
                val  eEnd= expenseYear.text.toString()
                val  eDesc= expenseDesc.text.toString()
                val  eCate= expenseCate.text.toString()
                //(Geeks For Geeks, 2025)
                if (eName.isBlank() || eDate.isBlank() ||
                    eStart.isBlank() || eEnd.isBlank() || eDesc.isBlank() || eCate.isBlank())
                //(W3Schools, 2025)
                {
                    Toast.makeText(this, "Empty inputs detected!", Toast.LENGTH_SHORT).show()
                }
                else
                //(W3Schools, 2025)
                {
                    Storage.expense.add(Expense(name = eName, price = eCost, day = eDate, month = eStart, year = eEnd, description = eDesc, category = eCate))
                    val intent = Intent(this, mainMenu::class.java)
                    startActivity(intent)
                    //(Geeks For Geeks, 2025)
                }

            }
            catch (e: Exception){
                Toast.makeText(this, "Invalid input detected!", Toast.LENGTH_SHORT).show()
            }
            //(Geeks For Geeks, 2025)
        }



    }

}
