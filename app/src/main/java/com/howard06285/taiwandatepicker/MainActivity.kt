package com.howard06285.taiwandatepicker

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.howard06285.taiwandatepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button.setOnClickListener {
            TaiwanDatePickerDialog.show(
                fragmentManager = supportFragmentManager,
                title = "選擇日期",
                useADYearFormat = false,
                initialDate = SimpleDate.now()
            ) { selectedDate ->
                val date = "${selectedDate.year}.${selectedDate.month}.${selectedDate.day}"
                binding.textview.text = date
                Log.d(tag, "TaiwanDatePickerDialog, date=$date")
            }
        }
    }
}