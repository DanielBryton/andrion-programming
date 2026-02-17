package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.editTextName)
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val phone = findViewById<EditText>(R.id.editTextPhone)
        val password = findViewById<EditText>(R.id.editTextNumberPassword)
        val signUpButton = findViewById<Button>(R.id.button2)

        signUpButton.setOnClickListener {
            val nameText = name.text.toString()
            val phoneText = phone.text.toString()
            val passwordText = password.text.toString()
            val emailText = email.text.toString()

            when {
                nameText.isEmpty() -> Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show()
                emailText.isEmpty() -> Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show()
                !Patterns.EMAIL_ADDRESS.matcher(emailText).matches() -> {
                    Toast.makeText(this, "Invalid email format (missing @ or domain)", Toast.LENGTH_SHORT).show()
                }
                phoneText.isEmpty() -> Toast.makeText(this, "Phone is empty", Toast.LENGTH_SHORT).show()
                passwordText.isEmpty() -> Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show()
                else -> {
                    // Save registration info
                    val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().apply {
                        putString("name", nameText)
                        putString("email", emailText)
                        apply()
                    }
                    
                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                    
                    // Navigate to Confirmation
                    val intent = Intent(this, ConfirmationActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
