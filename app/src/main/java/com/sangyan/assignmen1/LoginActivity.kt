package com.sangyan.assignmen1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        val login_button = findViewById<Button>(R.id.login_button)
        val sign_up_button = findViewById<Button>(R.id.signUp_button)
        val email_text= findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password_text = findViewById<EditText>(R.id.editTextTextPassword)
        sign_up_button.setOnClickListener{
            val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
            startActivity(intent)
        }
        login_button.setOnClickListener{
            val email = email_text.text.toString().trim()
            val password = password_text.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Sign in the user with the provided email and password
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                          val intent = Intent(this@LoginActivity,MainActivity::class.java)
                          startActivity(intent)

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
        }

    }
