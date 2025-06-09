package com.example.loginpageprac

import com.github.mikephil.charting.data.PieEntry

object Storage {
    public val entries = mutableListOf<PieEntry>()
    //(Kotlin, 2025)

    fun newEntry(numb: Float, name: String){
        entries.clear()
        entries.add(PieEntry(numb, name))
    }
    //(Kotlin, 2025)

}

