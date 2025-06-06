package com.example.loginpageprac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class MainActivity : AppCompatActivity() {

    lateinit var userInput : EditText
    lateinit var passInput : EditText
    lateinit var logPush : Button
    lateinit var signBtn : Button
    private val database = Firebase.database
    private val myRef = database.getReference("Credentials")
    //(Kotlin, 2025)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userInput = findViewById(R.id.user)
        passInput = findViewById(R.id.pass)
        logPush = findViewById(R.id.login_btn)
        signBtn = findViewById(R.id.Signup_btn)
        //(Android, 2025)

        logPush.setOnClickListener{

            val getUser = userInput.text.toString()
            val getPass = passInput.text.toString()

            myRef.addListenerForSingleValueEvent(object : ValueEventListener{

                override fun onDataChange(credSnapshot: DataSnapshot) {
                    var found = false


                    for (storedCredentials in credSnapshot.children){
                        val user = storedCredentials.child("username").getValue(String::class.java) ?: ""
                        val pass = storedCredentials.child("password").getValue(String::class.java) ?: ""

                        if (getUser == user && getPass == pass)
                        {

                            found = true
                            val intent = Intent(this@MainActivity, mainMenu::class.java)
                            startActivity(intent)
                            break
                        }
                    }
                    if (!found) {
                        Toast.makeText(this@MainActivity, "Invalid credentials!", Toast.LENGTH_SHORT).show()
                    }

                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        }

        signBtn.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
    }
}