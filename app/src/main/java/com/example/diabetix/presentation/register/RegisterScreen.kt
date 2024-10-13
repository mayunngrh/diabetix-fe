package com.example.diabetix.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diabetix.component.HintDatePicker
import com.example.diabetix.component.HintPasswordField
import com.example.diabetix.component.HintTextField
import com.example.diabetix.component.MyButton
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import java.util.Date

@Composable
fun RegisterScreen(
    navController: NavController
) {
    var nama by remember {
        mutableStateOf("")
    }

    var tanggal by remember {
        mutableStateOf<Date?>(null)
    }

    var email by remember {
        mutableStateOf("")
    }
    var pass by remember {
        mutableStateOf("")
    }
    var confPass by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Daftar",
            style = CustomTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            color = GreenNormal,
        )

        Spacer(modifier = Modifier.height(28.dp))

        HintTextField(hint = "Nama", onValueChange = { nama = it }, value = nama)
        Spacer(modifier = Modifier.height(24.dp))
        HintDatePicker(hint = "Tanggal Lahir", onValueChange = { tanggal = it }, value = tanggal)
        Spacer(modifier = Modifier.height(24.dp))
        HintTextField(hint = "Email", onValueChange = { email = it }, value = email)
        Spacer(modifier = Modifier.height(24.dp))
        HintPasswordField(hint = "Kata Sandi", onValueChange = { pass = it }, value = pass)
        Spacer(modifier = Modifier.height(24.dp))
        HintPasswordField(
            hint = "Konfirmasi Kata Sandi",
            onValueChange = { confPass = it },
            value = confPass
        )


        Spacer(modifier = Modifier.height(36.dp))
        MyButton(modifier = Modifier, onClick = {
            navController.navigate("verification")
        }, text = "Daftar")

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Sudah punya akun? ",
                style = CustomTheme.typography.p3,
                fontWeight = FontWeight.Medium
            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate("login")
                },
                text = "Masuk",
                style = CustomTheme.typography.p3,
                fontWeight = FontWeight.Medium,
                color = GreenNormal
            )
        }

    }
}