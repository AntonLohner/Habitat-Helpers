package com.example.habitathelpers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.example.habitathelpers.LoginFragment.OnLoginInteractionListener
import com.example.habitathelpers.MainFragment.OnMainInteractionListener
import com.example.habitathelpers.LoadFragment.OnLoadInteractionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnLoginInteractionListener, OnMainInteractionListener, OnLoadInteractionListener {

    // TODO: CreateActivity implementation
    // TODO: LoginActivity implementation
    // TODO: EditorActivity implementation
    // TODO: LoadActivity implementation
    // TODO: RegActivity implementation

    private var sub_opened: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.extras?.get("user")
        setContentView(R.layout.activity_main)

        // use custom toolbar
        // TODO: additional toolbar functionality
        setSupportActionBar(toolbar)

        //navigation drawer setup
        val toggle = ActionBarDrawerToggle(this, mainAct, toolbar, R.string.open_nav, R.string.close_nav)
        mainAct.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        //current default declaration so code runs
        //should be set to true if user is logged in
        if (user != null){
            // if logged in, inflate fragment_main and get buttons
            supportFragmentManager.beginTransaction().replace(R.id.linear2, MainFragment())
                .addToBackStack("")
                .commit()
            // replace toolbar name and user photo?


        }
        else{
            //if not logged in, inflate fragment_login and get buttons
            supportFragmentManager.beginTransaction().replace(R.id.linear2, LoginFragment())
                .addToBackStack("")
                .commit()
        }
    }

    //handle fragment button presses
    override fun onLoginInteraction(uri: View){
        // login button
        // navigate to LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    override fun onRegisterInteraction(uri: View){
        // register button
        // navigate to RegActivity
        val intent = Intent(this, RegActivity::class.java)
        startActivity(intent)
    }
    override fun onCreateInteraction(uri: View){
        // create new habitat button
        // navigate to CreateActivity
        val intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }
    override fun onLoadInteraction(uri: View){
        // load habitat button
        // inflate LoadFragment
        sub_opened = true
        supportFragmentManager.beginTransaction().replace(R.id.linear2, LoadFragment())
            .addToBackStack("")
            .commit()
    }

    //navigation drawer presses
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home-> {
                //do nothing

                }
            R.id.nav_create_new -> {
                val intent = Intent(this, CreateActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.nav_load -> {
                val intent = Intent(this, LoadActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
        }
        mainAct.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(mainAct.isDrawerOpen(GravityCompat.START)){
            mainAct.closeDrawer(GravityCompat.START)
        }
        else if (sub_opened){
            sub_opened = false
            // use super once to bring back button menu in fragment
            super.onBackPressed()
        }
        else {
            //use super twice to uninflate fragment and then navigate to previous activity
            super.onBackPressed()
            super.onBackPressed()
        }
    }
}
