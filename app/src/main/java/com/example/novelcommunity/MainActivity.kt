package com.example.novelcommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        val user=mAuth.currentUser
        if(user!=null)
        {
            val dashboardIntent = Intent(this, UserDashboard::class.java)
            //startActivity(dashboardIntent)
        }
    }
}