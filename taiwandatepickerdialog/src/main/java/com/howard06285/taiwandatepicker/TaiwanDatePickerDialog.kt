package com.howard06285.taiwandatepicker

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.time.LocalDate
import java.time.YearMonth
/**
 * Created by Howard on 2025/4/14
 *
 * A custom date picker dialog for selecting dates in the Taiwanese calendar format.
 *
 * @param initialDate The initial date to display in the picker.
 * @param useADYearFormat Boolean true:西元年、 false:民國年.
 * @param onDateSelected Callback function to be called when a date is selected.
 */
class TaiwanDatePickerDialog(
    private val title: String,
    initialDate: LocalDate = LocalDate.now(),
    val useADYearFormat: Boolean, // 是否使用西元年格式顯示
    private val onDateSelected: (LocalDate) -> Unit
) : DialogFragment() {

    private lateinit var titleTv: TextView
    private lateinit var yearPrefixTv: TextView
    private lateinit var npYear: NumberPicker
    private lateinit var npMonth: NumberPicker
    private lateinit var npDay: NumberPicker

    private var selectedYear = initialDate.year
    private var selectedMonth = initialDate.monthValue
    private var selectedDay = initialDate.dayOfMonth

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_taiwan_date_picker, null)

        titleTv = view.findViewById(R.id.textTitle)
        yearPrefixTv = view.findViewById(R.id.yearPrefixTextView)
        npYear = view.findViewById(R.id.np_year)
        npMonth = view.findViewById(R.id.np_month)
        npDay = view.findViewById(R.id.np_day)

        titleTv.text = title

        yearPrefixTv.visibility = if (useADYearFormat) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }

        setupYearPicker()
        setupMonthPicker()
        setupDayPicker()

        view.findViewById<Button>(R.id.btn_confirm).setOnClickListener {
            val result = LocalDate.of(selectedYear, selectedMonth, selectedDay)
            onDateSelected(result)
            dismiss()
        }

        builder.setView(view)
        val dialog = builder.create()

        // 設定圓角背景樣式
        dialog.setOnShowListener {
            dialog.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_corner_8dp)
        }

        return dialog
    }

    private fun setupYearPicker() {
        val currentYear = LocalDate.now().year
        val startYear = 1912
        val endYear = currentYear
//        val displayValues = (startYear..endYear).map { "民國 ${it - 1911} 年" }.toTypedArray()
        val displayValues = (startYear..endYear).map {
            if (useADYearFormat) "${it}" else "${it - 1911}"
        }.toTypedArray()

        npYear.minValue = 0
        npYear.maxValue = displayValues.size - 1
        npYear.displayedValues = displayValues

        val initialIndex = selectedYear - startYear
        npYear.value = initialIndex

        npYear.setOnValueChangedListener { _, _, newVal ->
            selectedYear = newVal + startYear
            updateDayPicker()
        }
    }

    private fun setupMonthPicker() {
//        val displayValues = (1..12).map { "${it} 月" }.toTypedArray()
        val displayValues = (1..12).map { "${it}" }.toTypedArray()
        npMonth.minValue = 1
        npMonth.maxValue = 12
        npMonth.displayedValues = displayValues
        npMonth.value = selectedMonth

        npMonth.setOnValueChangedListener { _, _, newVal ->
            selectedMonth = newVal
            updateDayPicker()
        }
    }

    private fun setupDayPicker() {
        updateDayPicker()

        npDay.setOnValueChangedListener { _, _, newVal ->
            selectedDay = newVal
        }
    }

    private fun updateDayPicker() {
        val daysInMonth = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth()
        npDay.minValue = 1
        npDay.maxValue = daysInMonth

        if (selectedDay > daysInMonth) {
            selectedDay = daysInMonth
        }

        npDay.value = selectedDay
    }

    companion object {
        fun show(
            fragmentManager: FragmentManager,
            title: String? = null,
            useADYearFormat: Boolean,
            initialDate: LocalDate = LocalDate.now(),
            onDateSelected: (LocalDate) -> Unit
        ) {
            TaiwanDatePickerDialog(
                title = title ?: "",
                initialDate = initialDate,
                useADYearFormat = useADYearFormat,
                onDateSelected = onDateSelected
            ).show(fragmentManager, "TaiwanDatePickerDialog")
        }
    }
}