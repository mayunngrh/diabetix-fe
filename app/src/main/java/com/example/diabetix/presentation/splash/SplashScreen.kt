package com.example.diabetix.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    val viewModel = hiltViewModel<SplashViewModel>()
    val isLogin = viewModel.isLogin.collectAsState()
    val user by viewModel.user.collectAsState()
    println("User SPLASH: $user")
    println("IsLogin : ${isLogin.value}")

    LaunchedEffect(Unit) {
        delay(4000)
        if (isLogin.value) {
            if (user != null) {
                println("USER NYA TIDAL NULL: $user")
                navController.navigate("homepage") {
                    popUpTo(navController.currentBackStackEntry?.destination?.route ?: "homepage") {
                        inclusive = true
                    }
                }
            } else {
                println("USER NYA NULL")
                navController.navigate("on_boarding")
            }


        } else {
            navController.navigate("on_boarding")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenNormal), contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.size(250.dp),
            model = R.drawable.logo_only,
            contentDescription = "Splash Logo"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp), contentAlignment = Alignment.BottomCenter
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "diabetix",
                    style = CustomTheme.typography.h2,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.27.em,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Sahabat Anda Menuju Hidup Sehat",
                    style = CustomTheme.typography.p4,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

            }
        }
    }

}