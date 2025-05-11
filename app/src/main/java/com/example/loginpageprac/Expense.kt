package com.example.loginpageprac

data class Expense(
    val name: String,
    val price: Float,
    val day: String,
    val month: String,
    val year: String,
    val description: String,
    val category: String
    //(Baueldung, 2025)
)

//val expenses = mutableListOf<Expense>()