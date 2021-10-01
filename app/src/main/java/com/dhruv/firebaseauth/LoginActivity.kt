package com.dhruv.firebaseauth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.dhruv.firebaseauth.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBind: ActivityLoginBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBind.root)

        // configure ActionBar
        actionBar = supportActionBar!!
        actionBar.title = "Login"

        // configure progress Dialog

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In")
        progressDialog.setCanceledOnTouchOutside(false)


        //init firebase
        firebaseAuth  = FirebaseAuth.getInstance()
        checkUser()

        // handle click, open register activity
        loginBind.noAccount.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        loginBind.loginBtn.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        email = loginBind.usernameEt.text.toString().trim()
        password = loginBind.passwordEt.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
             loginBind.usernameEt.error = "Invalid email format"
        } else if (TextUtils.isEmpty(password)){
            loginBind.passwordEt.error = "Please enter the password"
        } else {
            firebaseLogin()
        }

    }

    private fun firebaseLogin() {
        // show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()
                val firebaseUser  = firebaseAuth.currentUser
                val email = firebaseUser!!.email

                Toast.makeText(this, "Logged In as $email", Toast.LENGTH_SHORT).show()
                Log.i("before Login", "fine here")
                val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
                startActivity(intent)

                finish()
                Log.i("after Login", "fine here")
            }
            .addOnFailureListener { e->
                //login failed
                Toast.makeText(this, "Login Failed due to ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null ) {
             // user is logged in
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }

}