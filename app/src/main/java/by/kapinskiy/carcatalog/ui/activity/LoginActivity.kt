package by.kapinskiy.carcatalog.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kapinskiy.carcatalog.R

import by.kapinskiy.carcatalog.ui.viewmodel.UserAuthViewModel
import by.kapinskiy.carcatalog.util.Validator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val userAuthViewModel: UserAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.loginButton).setOnClickListener {
            val email = findViewById<EditText>(R.id.emailEditText).text.toString()
            val password = findViewById<EditText>(R.id.passwordEditText).text.toString()

            Validator.validateCredentials(email, password)?.let { errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userAuthViewModel.login(email, password)
        }

        val registerLink: TextView = findViewById(R.id.registerLink)
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            userAuthViewModel.uiState.collect { state ->
                if (state.isSuccess) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else if (!state.errorMessage.isNullOrEmpty()) {
                    Toast.makeText(this@LoginActivity, state.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
