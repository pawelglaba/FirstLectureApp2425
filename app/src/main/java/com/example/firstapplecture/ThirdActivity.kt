package com.example.firstapplecture

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/**
 * ThirdActivity is a demonstration screen using ConstraintLayout.
 * It shows a title and a button to go back to the previous activity.
 */

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val backButton: Button = findViewById(R.id.backButton)
        /**
         * Sets up a listener for the back button to return to the previous activity.
         */
        backButton.setOnClickListener {
            finish()
        }


    }
}