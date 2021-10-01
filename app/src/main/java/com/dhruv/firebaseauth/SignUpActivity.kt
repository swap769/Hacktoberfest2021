package com.dhruv.firebaseauth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.dhruv.firebaseauth.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var signBind: ActivitySignUpBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signBind = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signBind.root)
        firebaseAuth = FirebaseAuth.getInstance()
        actionBar = supportActionBar!!
        actionBar.title = "Sign Up"

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        // configure progress Dialog

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating account")
        progressDialog.setCanceledOnTouchOutside(false)

        signBind.signupBtn.setOnClickListener{
            validateData()
        }
    }
    private fun validateData() {
        email = signBind.usernameEt.text.toString().trim()
        password = signBind.passwordEt.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signBind.usernameEt.error = "Invalid email format"
        } else if (TextUtils.isEmpty(password)){
            signBind.passwordEt.error = "Please enter the password"
        } else if (password.length < 6) {
            signBind.passwordEt.error = "Password length must be at least 6 characters long"
        } else {
            firebaseSignUp()
//            val a = ""
        }

    }

    private fun firebaseSignUp() {
        // show progress
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email ,password)
            .addOnSuccessListener {
                // success
                val firebaseUser = firebaseAuth.currentUser
                val email  =  firebaseUser!!.email
                Toast.makeText(this, "SignUp success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignUpActivity, ProfileActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this, "SignUp failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // go back to the previous activity, when back button of actionbar clicked
        return super.onSupportNavigateUp()
    }

}