package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val confirmName = findViewById<EditText>(R.id.confirmName)
        val confirmEmail = findViewById<EditText>(R.id.confirmEmail)
        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val registeredName = sharedPreferences.getString("name", "")
        val registeredEmail = sharedPreferences.getString("email", "")

        buttonConfirm.setOnClickListener {
            val inputName = confirmName.text.toString()
            val inputEmail = confirmEmail.text.toString()

            if (inputName == registeredName && inputEmail == registeredEmail) {
                Toast.makeText(this, "Identity Confirmed!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LandingActivity::class.java)
                startActivity(intent)
                finish() // Close confirmation page
            } else {
                Toast.makeText(this, "Incorrect details. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
