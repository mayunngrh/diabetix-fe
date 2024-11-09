package com.example.diabetix.presentation.verification

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MyButton
import com.example.diabetix.component.OTPInputField
import com.example.diabetix.data.request.VerifyRequest
import com.example.diabetix.presentation.login.LoginState
import com.example.diabetix.presentation.login.LoginViewModel
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal
import com.example.diabetix.ui.theme.NetralNormalActive
import kotlinx.coroutines.delay

@Composable
fun VerificationScreen(
    navController: NavController
) {

    var otp by remember {
        mutableStateOf("")
    }

    val viewModel = hiltViewModel<VerificationViewModel>()
    val verificationState by viewModel.verificationState.collectAsState()
    val id by viewModel.idFlow.collectAsState(initial = "")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            modifier = Modifier
                .size(250.dp)
                .padding(24.dp),
            model = R.drawable.vector_verification,
            contentDescription = "Verification Vector"
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Verifikasi Email",
            style = CustomTheme.typography.h2,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            text = "Masukkan Kode Verifikasi yang sudah di kirim ke Alamat Email Anda",
            style = CustomTheme.typography.p3,
            textAlign = TextAlign.Center,
            color = NetralNormalActive
        )

        Spacer(modifier = Modifier.height(24.dp))

        OTPInputField(otp = otp, onOtpChange = { otp = it })

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Tidak menerima kode OTP? ",
                style = CustomTheme.typography.p3,
                fontWeight = FontWeight.Medium
            )
            Text(
                modifier = Modifier.clickable {

                },
                text = "Kirim ulang",
                style = CustomTheme.typography.p3,
                fontWeight = FontWeight.Medium,
                color = GreenNormal
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        MyButton(modifier = Modifier.padding(horizontal = 24.dp), onClick = {

            val request = VerifyRequest(id,otp)
            viewModel.verify(request)

        }, text = "Verifikasi")

        Spacer(modifier = Modifier.height(64.dp))

    }

    //LOADING AND PROSES
    Box(modifier = Modifier.fillMaxSize()) {
        when (verificationState) {
            is VerificationState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(NetralNormal.copy(0.4f)), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = GreenNormal, modifier = Modifier.size(64.dp))
                }

            }

            is VerificationState.Success -> {
                LaunchedEffect(Unit) {
                    delay(200)
                    navController.navigate("personalization")
                }
            }

            is VerificationState.Error -> {
                val errorMessage = (verificationState as VerificationState.Error).message
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(
                            onClick = { viewModel.resetVerificationState() },
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