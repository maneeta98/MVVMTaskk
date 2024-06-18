package com.example.chaipani

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import com.example.chaipani.ui.dashboard.MainDashboardActivity
import com.example.chaipani.ui.loginpage.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // Initialize SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        Handler(Looper.getMainLooper()).postDelayed({
           checkLogin()
        },3000)

//        Thread.sleep(3000)

//        installSplashScreen()
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signuplogo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun checkLogin() {
        val savedEmail = sharedPreferences.getString("EMAIL", null)
        val savedPassword = sharedPreferences.getString("PASSWORD", null)

        if (savedEmail != null && savedPassword != null) {

                Toast.makeText(this, "Auto Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SplashScreenActivity, MainDashboardActivity::class.java)
//                        val currentUser = auth.currentUser.toString()
//                        val userName = auth.currentUser?.displayName.toString()
//                        intent.putExtra("email", currentUser)
//                        intent.putExtra("name", userName)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Auto Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)

                startActivity(intent)
                finish()
                Toast.makeText(this, "Auto Login Failed.", Toast.LENGTH_SHORT).show()
            }

    }

}