package com.example.diabetix

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diabetix.presentation.forgot_password.ForgotPassword
import com.example.diabetix.presentation.homepage.HomepageScreen
import com.example.diabetix.presentation.login.LoginScreen
import com.example.diabetix.presentation.new_password.NewPasswordScreen
import com.example.diabetix.presentation.on_boarding.OnBoardingScreen
import com.example.diabetix.presentation.personalization.PersonalizationScreen
import com.example.diabetix.presentation.register.RegisterScreen
import com.example.diabetix.presentation.splash.SplashScreen
import com.example.diabetix.presentation.verification.VerificationScreen
import com.example.diabetix.ui.theme.DiabetixTheme
import dagger.hilt.android.AndroidEntryPoint

lateinit var navController: NavHostController
lateinit var viewModel: MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel = hiltViewModel<MainViewModel>()
            navController = rememberNavController()

            navController.addOnDestinationChangedListener { _, destination, _ ->
                destination?.route.let {
                    viewModel.currentRoute.value = it.toString()

                    ///UPDATE
                    when (it) {
                        "beranda", "history", "profile" -> viewModel.showBottomBar.value =
                            true

                        else -> viewModel.showBottomBar.value = false
                    }
                }
            }

            DiabetixTheme {
                Scaffold(
                    //BUAT BUTTOM BAR
                ) {
                    NavHost(navController = navController, startDestination = "homepage") {
                        composable("splash"){
                            SplashScreen(navController = navController)
                        }
                        composable("on_boarding") {
                            OnBoardingScreen(navController = navController)
                        }
                        composable("login"){
                            LoginScreen(navController = navController)
                        }
                        composable("register"){
                            RegisterScreen(navController = navController)
                        }
                        composable("verification") {
                            VerificationScreen(navController = navController)
                        }
                        composable("personalization"){
                            PersonalizationScreen(navController = navController)
                        }
                        composable("forgot_password"){
                            ForgotPassword(navController = navController)
                        }
                        composable("new_password"){
                            NewPasswordScreen(navController = navController)
                        }
                        composable("homepage") {
                            HomepageScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}