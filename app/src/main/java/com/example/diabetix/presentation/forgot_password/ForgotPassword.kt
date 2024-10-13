package com.example.diabetix.presentation.forgot_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.diabetix.component.HintTextField
import com.example.diabetix.component.MyButton
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun ForgotPassword(
    navController: NavController
) {
    var email by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Masukkan Email",
            style = CustomTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            color = GreenNormal,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(24.dp))

        HintTextField(hint = "Email", onValueChange = { email = it }, value = email)

        Spacer(modifier = Modifier.height(48.dp))

        MyButton(modifier = Modifier, onClick = { /*TODO*/ }, text = "Perbarui Kata Sandi")
    }
}