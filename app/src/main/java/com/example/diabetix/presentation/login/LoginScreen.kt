package com.example.diabetix.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.diabetix.component.GoogleButton
import com.example.diabetix.component.HintPasswordField
import com.example.diabetix.component.HintTextField
import com.example.diabetix.component.MyButton
import com.example.diabetix.data.request.LoginRequest
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }

    var pass by remember {
        mutableStateOf("")
    }

    val viewModel = hiltViewModel<LoginViewModel>()
    val loginState by viewModel.loginState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Masuk",
            style = CustomTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            color = GreenNormal,
        )
        Spacer(modifier = Modifier.height(32.dp))

        HintTextField(hint = "Email", onValueChange = { email = it }, value = email)

        Spacer(modifier = Modifier.height(24.dp))

        HintPasswordField(hint = "Password", onValueChange = { pass = it }, value = pass)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier.clickable {
                navController.navigate("forgot_password")
            },
            text = "Lupa Password?",
            style = CustomTheme.typography.p3,
            color = GreenNormal,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(56.dp))

        MyButton(modifier = Modifier, onClick = {
            val request = LoginRequest(email,pass)
            viewModel.login(request)
        }, text = "Masuk")

        Spacer(modifier = Modifier.height(24.dp))

        GoogleButton(modifier = Modifier, onClick = {
            Toast.makeText(
                context,
                "Sedang dalam tahap pengembangan, silahkan daftar menggunakan email",
                Toast.LENGTH_SHORT
            ).show()
        })

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Belum punya akun? ",
                style = CustomTheme.typography.p3,
                fontWeight = FontWeight.Medium
            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate("register")
                },
                text = "Daftar akun",
                style = CustomTheme.typography.p3,
                fontWeight = FontWeight.Medium,
                color = GreenNormal
            )
        }
    }

    //LOADING AND PROSES
    Box(modifier = Modifier.fillMaxSize()) {
        when (loginState) {
            is LoginState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(NetralNormal.copy(0.4f)), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = GreenNormal, modifier = Modifier.size(64.dp))
                }

            }

            is LoginState.Success -> {
                LaunchedEffect(Unit) {
                    delay(200)
                    navController.navigate("homepage")
                }
            }

            is LoginState.Error -> {
                val errorMessage = (loginState as LoginState.Error).message
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(
                            onClick = { viewModel.resetLoginState() },
                            colors = ButtonDefaults.buttonColors(
                                GreenNormal
                            )
                        ) {
                            Text("OK")
                        }
                    },
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(text = errorMessage ?: "Unknown error occurred.")
                    }
                )
            }

            else -> {
                //
            }
        }
    }
}