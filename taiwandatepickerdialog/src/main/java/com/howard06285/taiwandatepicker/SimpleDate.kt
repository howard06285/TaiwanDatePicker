package com.howard06285.taiwandatepicker

import java.util.Calendar

/**
 * A simple date class that works on all Android API levels without requiring desugaring.
 *
 * @param year The year (e.g., 2024)
 * @param month The month (1-12, where 1 = January, 12 = December)
 * @param day The day of month (1-31)
 */
data class SimpleDate(
    val year: Int,
    val month: Int,
    val day: Int
) {
    companion object {
        /**
         * Creates a SimpleDate from the current date.
         */
        fun now(): SimpleDate {
            val calendar = Calendar.getInstance()
            return SimpleDate(
                year = calendar.get(Calendar.YEAR),
                month = calendar.get(Calendar.MONTH) + 1, // Calendar.MONTH is 0-based
                day = calendar.get(Calendar.DAY_OF_MONTH)
            )
        }
        
        /**
         * Creates a SimpleDate from a Calendar instance.
         */
        fun from(calendar: Calendar): SimpleDate {
            return SimpleDate(
                year = calendar.get(Calendar.YEAR),
                month = calendar.get(Calendar.MONTH) + 1, // Calendar.MONTH is 0-based
                day = calendar.get(Calendar.DAY_OF_MONTH)
            )
        }
    }
    
    /**
     * Converts this SimpleDate to a Calendar instance.
     */
    fun toCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day) // Calendar.MONTH is 0-based
        return calendar
    }
    
    /**
     * Gets the Taiwan year (民國年).
     */
    val taiwanYear: Int
        get() = year - 1911
    
    /**
     * Returns the number of days in this month.
     */
    fun daysInMonth(): Int {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, 1) // Calendar.MONTH is 0-based
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }
    
    override fun toString(): String {
        return "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}"
    }
}