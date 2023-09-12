package com.gracie.billz.utils

import android.text.format.Time.MONDAY
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.text.temporal.TemporalAdjusters.*
import java.time.temporal.TemporalAdjusters.previousOrSame

class DateTimeUtils {
    companion object{

        fun getFirstDayOfWeek(): String{
            val now = LocalDateTime.now()
            val first = now.with(previousOrSame(MONDAY))
            return formatDateDDMMYY(first)

        }       fun geLastDayOfWeek() : String{
            val now = LocalDateTime.now()
            val first = now.with(previousOrSame(MONDAY))
            return formatDateDDMMYY(first)

        }          fun formatDateDDMMYY(localDate: LocalDateTime) :String{
            val sdf = SimpleDateFormat("DD/MM/YYYY")
            return  sdf.format((localDate))

        }



    }
}