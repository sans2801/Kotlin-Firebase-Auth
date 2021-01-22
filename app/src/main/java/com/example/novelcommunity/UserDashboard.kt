package com.example.novelcommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import model.userModel

class UserDashboard : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mAuthStateListener : FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)
        val button: Button = findViewById(R.id.logout_button)
        //Logout Button
        button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val loginIntent = Intent(this, MainActivity::class.java)
            startActivity(loginIntent)
        }

        val reference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users").child(FirebaseAuth.getInstance().currentUser!!.uid.toString())
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists())
                {
//                    var userDetails= mutableListOf<String>()
//                    for(data in snapshot.children)
//                        userDetails.add(data.toString())
                    var user = snapshot.getValue(userModel::class.java)
                    findViewById<TextView>(R.id.detail_email).text=user!!.email
                    findViewById<TextView>(R.id.detail_username).text=user!!.username

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}