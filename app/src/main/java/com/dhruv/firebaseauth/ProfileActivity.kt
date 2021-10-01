package com.dhruv.firebaseauth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dhruv.firebaseauth.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileBind: ActivityProfileBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        profileBind = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBind.root)

        // action bar
        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        firebaseAuth = FirebaseAuth.getInstance()

        checkUser()

        // on press logout
        profileBind.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null) {
            val email =   firebaseUser.email
            profileBind.userEmail.text = email
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}