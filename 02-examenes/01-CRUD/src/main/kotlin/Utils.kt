package org.example

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        fun dateToString(date: Date): String {
            return dateFormat.format(date)
        }

        fun stringToDate(date: String): Date {
            return dateFormat.parse(date)
        }

        fun pause () {
            println("Press Any key to continue...")
            readlnOrNull()
        }
    }
}