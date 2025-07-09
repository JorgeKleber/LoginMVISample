package com.example.loginmvisample.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginmvisample.presentation.intent.LoginIntent
import com.example.loginmvisample.presentation.state.LoginState
import com.example.loginmvisample.presentation.viewmodel.LoginViewModel
import com.example.loginmvisample.ui.theme.LoginMVISampleTheme

@Composable
fun loginScreen(
    viewModel: LoginViewModel
) {
    LoginMVISampleTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                loginCard(viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun loginCard(
    viewModel: LoginViewModel = LoginViewModel()
) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by viewModel.state.collectAsState()

    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(350.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                value = username,
                label = { Text("Username") },
                onValueChange = { username = it }
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                value = password,
                label = { Text("Password") },
                onValueChange = { password = it }
            )
            ElevatedButton(
                onClick = {
                    viewModel.ValidateUser(LoginIntent.SubmitLogin(username, password))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
            Text(
                modifier = Modifier
                    .padding( 10.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                text = "Status: ${
                    when (state) {
                        is LoginState.Approved -> "Approved"
                        is LoginState.Failed -> "Failed"
                        else -> "Idle"
                    }
                }")
        }
    }
}


