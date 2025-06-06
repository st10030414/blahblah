package com.example.loginpageprac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database

//(Geeks For Geeks, 2025)

class Entry : AppCompatActivity() {


    private lateinit var expenseName: EditText
    private lateinit var expenseCost: EditText
    private lateinit var expenseDay: EditText
    private lateinit var expenseMonth: EditText
    private lateinit var expenseYear: EditText
    private lateinit var expenseDesc: EditText
    private lateinit var expenseCate: EditText
    private val database = Firebase.database
    private val myRef = database.getReference("Expense_Entry")
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
                val eCost = expenseCost.text.toString().toFloatOrNull()
                val  eDay= expenseDay.text.toString().toFloatOrNull()
                val  eMonth= expenseMonth.text.toString().toFloatOrNull()
                val  eYear= expenseYear.text.toString().toFloatOrNull()
                val  eDesc= expenseDesc.text.toString()
                val  eCate= expenseCate.text.toString()
                //(Geeks For Geeks, 2025)
                if (eName.isBlank() || eDay == null ||
                    eMonth == null || eYear == null ||
                    eDesc.isBlank() || eCate.isBlank() || eCost == null)
                //(W3Schools, 2025)
                {
                    Toast.makeText(this, "Empty inputs detected!", Toast.LENGTH_SHORT).show()
                }
                else
                //(W3Schools, 2025)
                {

                    val newExp = mapOf(
                        "name" to eName,
                        "cost" to eCost,
                        "day" to eDay,
                        "month" to eMonth,
                        "year" to eYear,
                        "description" to eDesc,
                        "category" to eCate
                    )
                    myRef.push().setValue(newExp).addOnSuccessListener {
                        Toast.makeText(this, "User details successfully entered!", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "User details successfully entered!", Toast.LENGTH_SHORT).show()
                    }
                    val intent = Intent(this, mainMenu::class.java)
                    startActivity(intent)
                }

            }
            catch (e: Exception){
                Toast.makeText(this, "Invalid input detected!", Toast.LENGTH_SHORT).show()
            }
            //(Geeks For Geeks, 2025)
        }



    }

}
