package com.example.habitathelpers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var auth: FirebaseAuth
    internal lateinit var userNameET: EditText
    internal lateinit var passwordET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_login)

        //Initialize an instance of FirebaseAuth
        userNameET = findViewById<EditText>(R.id.edit_text_email)
        passwordET = findViewById<EditText>(R.id.edit_text_password)

        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {
            login()
        }
        val createButton = findViewById<Button>(R.id.create_user)
        createButton.setOnClickListener {
            createUser()
        }

        // use custom toolbar
        // I've disabled the supportActionBar on the login activity, as a user would have to login
//         or register before creating a new projects.

//        setSupportActionBar(toolbar)
//
//        //navigation drawer setup
//        val toggle = ActionBarDrawerToggle(this, mainAct, toolbar, R.string.open_nav, R.string.close_nav)
//        mainAct.addDrawerListener(toggle)
//        toggle.syncState()
//
//        navView.setNavigationItemSelectedListener(this)
    }

    // TODO: user login via firebase

    //login
    fun login() {
        auth.signInWithEmailAndPassword(userNameET.text.toString(), passwordET.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    startActivity(user)
                } else {
                    Log.w("aaa", "signInWithEmail:failure", task.exception)
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed: "+task.exception,
                        Toast.LENGTH_SHORT).show()
                }


            }

    }

    private fun startActivity(user: FirebaseUser?){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    fun createUser() {
        auth.createUserWithEmailAndPassword(userNameET.text.toString(),
            passwordET.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    startActivity(user)
                } else {
                    Log.w("aaa", "createUserWithEmail:failure", task.exception)
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed: "+task.exception,
                        Toast.LENGTH_SHORT).show()
                }
                // ...
            }
    }

    //navigation drawer presses
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.nav_home-> {
//                val intent = Intent(this, MainActivity::class.java)
//                intent.putExtra("action", 0)
//                startActivity(intent)
//            }
//            R.id.nav_create_new -> {
//                val intent = Intent(this, CreateActivity::class.java)
//                intent.putExtra("action", 0)
//                startActivity(intent)
//            }
//            R.id.nav_load -> {
//                val intent = Intent(this, LoadActivity::class.java)
//                intent.putExtra("action", 0)
//                startActivity(intent)
//            }
//        }
//        mainAct.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
//        if(mainAct.isDrawerOpen(GravityCompat.START)){
//            mainAct.closeDrawer(GravityCompat.START)
//        }
//        else {
            super.onBackPressed()
//        }
    }

}