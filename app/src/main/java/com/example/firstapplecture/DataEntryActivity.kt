package com.example.firstapplecture

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.app.DatePickerDialog
import java.util.*


/**
 * DataEntryActivity is the first screen where users can enter their data.
 * It includes fields for nickname, email, birth date, gender, height, weight, and activity level.
 */
class DataEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_entry)

        val nickEditText: EditText = findViewById(R.id.nickEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val birthDateButton: Button = findViewById(R.id.birthDateButton)
        val birthDateTextView: TextView = findViewById(R.id.birthDateTextView)
        val genderRadioGroup: RadioGroup = findViewById(R.id.genderRadioGroup)
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val activitySeekBar: SeekBar = findViewById(R.id.activitySeekBar)
        val activityLevelTextView: TextView = findViewById(R.id.activityLevelTextView)
        val saveButton: Button = findViewById(R.id.saveButton)


        /**
         * Sets up a listener for the birth date button to display a DatePicker dialog.
         */
        birthDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val dateString = "$selectedDay-${selectedMonth + 1}-$selectedYear"
                birthDateTextView.text = dateString
            }, year, month, day)
            datePickerDialog.show()
        }


        /**
         * Sets up a listener for the activity seek bar to update the activity level text view.
         */

        activitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                activityLevelTextView.text = "Activity Level: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        /**
         * Sets up a listener for the save button to validate input, save data, and navigate to the next activity.
         */
        saveButton.setOnClickListener {
            val nick = nickEditText.text.toString()
            val email = emailEditText.text.toString()
            val birthDate = birthDateTextView.text.toString()
            val genderId = genderRadioGroup.checkedRadioButtonId
            val gender = when (genderId) {
                R.id.radioMale -> Gender.MALE
                R.id.radioFemale -> Gender.FEMALE
                R.id.radioPreferNotToSay -> Gender.PREFER_NOT_TO_SAY
                else -> null
            }
            val height = heightEditText.text.toString().toFloatOrNull()
            val weight = weightEditText.text.toString().toFloatOrNull()
            val activityLevel = activitySeekBar.progress

            if (nick.isNotBlank() && email.isNotBlank() && birthDate.isNotBlank() && gender != null && height != null && weight != null) {
                val userData = UserData(nick, email, birthDate, gender, height, weight, activityLevel)
                Toast.makeText(this, "Data saved: $userData", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userData", userData)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter valid data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


