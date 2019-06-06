package com.example.appsdoppler

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainAc : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth
    private var logged = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mai)


        txtUser=findViewById(R.id.txtUser)
        txtPassword=findViewById(R.id.txtPassword)
        progressBar= findViewById(R.id.progressBar)
        auth= FirebaseAuth.getInstance()

    }

    fun forgotPassword(view:View){
        startActivity(Intent(this, Password::class.java))
    }
    fun register(view:View){
        startActivity(Intent(this, SignUp::class.java))
    }
    fun login(view:View){
        loginUser()
    }

    private fun loginUser(){
        val user:String=txtUser.text.toString()
        val password:String=txtPassword.text.toString()

        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {
            progressBar.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                        task->

                    if(task.isSuccessful){
                        action()
                        logged = true
                    }else{
                        Toast.makeText(this,"Error en la autenticacion", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}
