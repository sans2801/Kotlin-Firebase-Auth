package com.example.novelcommunity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import model.userModel
import org.jetbrains.annotations.NotNull

class login : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view:View = inflater!!.inflate(R.layout.fragment_login, container, false)

        mAuth = FirebaseAuth.getInstance()

        mAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth : FirebaseAuth -> run {

            val user: FirebaseUser? = mAuth.currentUser
            if (user!=null) {
                val dashboardIntent = Intent(activity, UserDashboard::class.java)
                startActivity(dashboardIntent)
            }
        }

        }

        val button = view.findViewById<Button>(R.id.signup_redirect)
        val loginButton = view.findViewById<Button>(R.id.login_button)
        button.setOnClickListener { signupRedirect(view) }
        loginButton.setOnClickListener { user_login(view) }
        return view
    }

    fun signupRedirect(view:View){
        view.findNavController().navigate(R.id.action_login_to_signup)
    }

    fun user_login(view:View){

        val email = view.findViewById<EditText>(R.id.login_email).text.toString().trim()
        val password = view.findViewById<EditText>(R.id.login_password).text.toString().trim()

        if (email.isEmpty())
        {
            view.findViewById<EditText>(R.id.login_email).error="Please provide a valid E-mail"
            return
        }

        if (password.isEmpty())
        {
            view.findViewById<EditText>(R.id.login_password).error="Please enter a password"
            return
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(){task->
            if(task.isSuccessful) {
                val dashboardIntent = Intent(activity, UserDashboard::class.java)
                startActivity(dashboardIntent)
            }
            else{
                Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }


        }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener { mAuthStateListener }

    }


    }






