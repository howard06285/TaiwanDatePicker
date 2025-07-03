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
import java.util.Calendar

/**
 * A custom date picker dialog for selecting dates in the Taiwanese calendar format.
 *
 * @param initialDate The initial date to display in the picker.
 * @param useADYearFormat Boolean true:西元年、 false:民國年.
 * @param onDateSelected Callback function to be called when a date is selected.
 */
class TaiwanDatePickerDialog(
    private val title: String,
    initialDate: SimpleDate = SimpleDate.now(),
    val useADYearFormat: Boolean, // 是否使用西元年格式顯示
    private val onDateSelected: (SimpleDate) -> Unit
) : DialogFragment() {

    private lateinit var titleTv: TextView
    private lateinit var yearPrefixTv: TextView
    private lateinit var npYear: NumberPicker
    private lateinit var npMonth: NumberPicker
    private lateinit var npDay: NumberPicker

    private var selectedYear = initialDate.year
    private var selectedMonth = initialDate.month
    private var selectedDay = initialDate.day

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
            val result = SimpleDate(selectedYear, selectedMonth, selectedDay)
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
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val startYear = 1912
        val endYear = currentYear + 10 // Allow future years
        
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
        val daysInMonth = SimpleDate(selectedYear, selectedMonth, 1).daysInMonth()
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
            initialDate: SimpleDate = SimpleDate.now(),
            onDateSelected: (SimpleDate) -> Unit
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