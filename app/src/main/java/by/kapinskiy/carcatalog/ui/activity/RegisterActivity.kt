package by.kapinskiy.carcatalog.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kapinskiy.carcatalog.R
import by.kapinskiy.carcatalog.ui.viewmodel.UserAuthViewModel
import by.kapinskiy.carcatalog.util.Validator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private val userAuthViewModel: UserAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        lifecycleScope.launch {
            userAuthViewModel.uiState.collect { uiState ->
                if (uiState.isSuccess) {
                    startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                    finish()
                }  else if (!uiState.errorMessage.isNullOrEmpty()) {
                    Toast.makeText(this@RegisterActivity, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        findViewById<TextView>(R.id.registerButton).setOnClickListener {
            val email = findViewById<EditText>(R.id.emailEditText).text.toString()
            val password = findViewById<EditText>(R.id.passwordEditText).text.toString()

            Validator.validateCredentials(email, password)?.let { errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            userAuthViewModel.register(email, password)
        }
    }
}
