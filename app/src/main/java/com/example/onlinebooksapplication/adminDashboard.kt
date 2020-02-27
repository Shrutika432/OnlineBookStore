package com.example.onlinebooksapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_admin_dashboard.*

class adminDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)
        cardbook.setOnClickListener {
            var intent=Intent(this,managebook::class.java)
            startActivity(intent)
        }
    }
}
