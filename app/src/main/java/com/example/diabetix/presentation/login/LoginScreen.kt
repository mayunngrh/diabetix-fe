package com.example.diabetix.presentation.login

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
import com.example.diabetix.component.GoogleButton
import com.example.diabetix.component.HintPasswordField
import com.example.diabetix.component.HintTextField
import com.example.diabetix.component.MyButton
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun LoginScreen(
    navController: NavController
) {
    var email by remember {
        mutableStateOf("")
    }

    var pass by remember {
        mutableStateOf("")
    }

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
                //DO SOMETHING
            },
            text = "Lupa Password?",
            style = CustomTheme.typography.p3,
            color = GreenNormal,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(56.dp))

        MyButton(modifier = Modifier, onClick = { /*TODO*/ }, text = "Masuk")

        Spacer(modifier = Modifier.height(24.dp))

        GoogleButton(modifier = Modifier, onClick = {

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

                },
                text = "Daftar akun",
                style = CustomTheme.typography.p3,
                fontWeight = FontWeight.Medium,
                color = GreenNormal
            )
        }
    }
}