package com.example.onlinebooksapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_homepage.*

class homepage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        btnlogin.setOnClickListener {
            Toast.makeText(this,"Login pressed",Toast.LENGTH_LONG).show()
        }
        textSignup.setOnClickListener {
            var intent=Intent(this,signup::class.java)
            startActivity(intent)
        }
    }
}
