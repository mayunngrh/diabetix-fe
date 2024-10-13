package com.example.diabetix.presentation.verification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MyButton
import com.example.diabetix.component.OTPInputField
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormalActive

@Composable
fun VerificationScreen(
    navController: NavController
) {

    var otp by remember {
        mutableStateOf("")
    }

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
        
        OTPInputField(otp = otp, onOtpChange = {otp=it})

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

        MyButton(modifier = Modifier.padding(horizontal = 24.dp), onClick = { /*TODO*/ }, text = "Verifikasi")

        Spacer(modifier = Modifier.height(64.dp))

    }

}