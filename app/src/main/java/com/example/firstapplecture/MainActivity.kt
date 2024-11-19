package com.example.firstapplecture

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar
import kotlin.math.pow


/**
 * MainActivity is the second screen where users can calculate their BMI and daily calorie needs.
 * It retrieves user data from the previous activity and allows calculations based on that data.
 */

class MainActivity : AppCompatActivity() {


    /**
     * Calculates the BMI (Body Mass Index) based on the user's weight and height.
     *
     * @param weight The weight of the user in kilograms.
     * @param height The height of the user in centimeters.
     * @return The calculated BMI value.
     */
    private fun calculateBmi(weight: Float, height: Float): Float {
        return weight / (height / 100).pow(2)
    }

    /**
     * Calculates the BMR (Basal Metabolic Rate) based on the user's weight, height, age, and gender.
     *
     * @param weight The weight of the user in kilograms.
     * @param height The height of the user in centimeters.
     * @param age The age of the user in years.
     * @param gender The gender of the user.
     * @param activityLevel The activity level of the user.
     * @return The calculated BMR value.
     */
    private fun calculateBmr(weight: Float, height: Float, age: Int, gender: Gender, activityLevel: Int): Number{
        var bmr = when (gender) {
            Gender.MALE -> 10 * weight + 6.25 * height - 5 * age + 5
            Gender.FEMALE -> 10 * weight + 6.25 * height - 5 * age - 161
            Gender.PREFER_NOT_TO_SAY -> 10 * weight + 6.25 * height - 5 * age
        }
        bmr *= when (activityLevel) {
            1 -> 1.2f // No activity
            2 -> 1.375f // Light activity
            3 -> 1.55f // Moderate activity
            4 -> 1.725f // High activity
            else -> 1.2f
        }
        return bmr
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userData = intent.getSerializableExtra("userData") as? UserData
        val calculateBmiButton: Button = findViewById(R.id.calculateBmiButton)
        val calculateCaloriesButton: Button = findViewById(R.id.calculateCaloriesButton)
        val bmiTextView: TextView = findViewById(R.id.bmiTextView)
        val bmrTextView: TextView = findViewById(R.id.bmrTextView)


        val goToThirdActivityButton: Button = findViewById(R.id.goToThirdActivityButton)
        goToThirdActivityButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }


        /**
         * Sets up a listener for the calculate BMI button to calculate and display the user's BMI.
         */

        calculateBmiButton.setOnClickListener {
            if (userData != null) {
                val height = userData.height
                val weight = userData.weight
                val bmi = calculateBmi(weight, height)
                bmiTextView.text = "Your BMI: %.2f".format(bmi)
                Toast.makeText(this, "Your BMI: %.2f".format(bmi), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show()
            }
        }
        /**
         * Sets up a listener for the calculate calories button to calculate and display the user's daily calorie needs.
         */
        calculateCaloriesButton.setOnClickListener {
            if (userData != null) {
                val weight = userData.weight
                val height = userData.height
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                val birthYear = userData.birthDate.split("-").last().toIntOrNull()
                val age = if (birthYear != null) currentYear - birthYear else 18
                val activityLevel = userData.activityLevel
                val gender = userData.gender

                val bmr = calculateBmr(weight, height, age, gender, activityLevel)



                bmrTextView.text = "Your BMR: %.2f".format(bmr)
                Toast.makeText(this, "Your BMI: %.2f".format(bmr), Toast.LENGTH_LONG).show()

                Toast.makeText(this, "Your daily calorie needs: %.2f kcal".format(bmr), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}