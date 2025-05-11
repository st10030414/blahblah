package com.example.loginpageprac

import com.github.mikephil.charting.data.PieEntry

object Storage {
    public val entries = mutableListOf<PieEntry>()
    public val income = mutableListOf<Income>()
    public val expense = mutableListOf<Expense>()
    public val goal = mutableListOf<GoalHolder>()
    //(Kotlin, 2025)

    fun newEntry(numb: Float, name: String){
        entries.add(PieEntry(numb, name))
    }
    //(Kotlin, 2025)

}

