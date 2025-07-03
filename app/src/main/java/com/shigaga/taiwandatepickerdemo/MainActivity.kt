package com.shigaga.taiwandatepickerdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shigaga.taiwandatepicker.TaiwanDatePickerDialog
import com.shigaga.taiwandatepickerdemo.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button.setOnClickListener {
            TaiwanDatePickerDialog.show(
                fragmentManager = supportFragmentManager,
                useADYearFormat = false,
                initialDate = LocalDate.now()
            ) { selectedDate ->
                val date = "${selectedDate.year - 1911}.${selectedDate.monthValue}.${selectedDate.dayOfMonth}"
                binding.textview.text = date
                Log.d(tag, "TaiwanDatePickerDialog, date=$date")
            }
        }

    }
}