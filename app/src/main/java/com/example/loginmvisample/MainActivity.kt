package com.example.loginmvisample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.loginmvisample.presentation.view.loginScreen
import com.example.loginmvisample.presentation.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {


    private val viewModel : LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            loginScreen(viewModel)
        }
    }
}