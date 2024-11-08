package com.example.diabetix

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.diabetix.ui.theme.GreenNormal
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.Icon
import coil.imageLoader
import com.example.diabetix.presentation.add_bmi.AddBmi
import com.example.diabetix.presentation.analyze_page.AnalyzePageScreen
import com.example.diabetix.presentation.analyze_result.AnalyzeResultScreen
import com.example.diabetix.presentation.article.ArticlePage
import com.example.diabetix.presentation.bmi.BmiScreen
import com.example.diabetix.presentation.consultation.ConsultationPage
import com.example.diabetix.presentation.daily_sugar.DailySugarScreen
import com.example.diabetix.presentation.profile.ProfilePage
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.NetralNormal
import com.example.diabetix.ui.theme.NetralNormalActive

lateinit var navController: NavHostController
lateinit var viewModel: MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                GreenNormal.toArgb(),
                GreenNormal.toArgb()
            )
        )

        super.onCreate(savedInstanceState)
        setContent {
            viewModel = hiltViewModel<MainViewModel>()
            navController = rememberNavController()

            navController.addOnDestinationChangedListener { _, destination, _ ->
                destination?.route.let {
                    viewModel.currentRoute.value = it.toString()

                    ///UPDATE
                    when (it) {
                        "homepage", "consultation", "article", "profile" -> viewModel.showBottomBar.value =
                            true

                        else -> viewModel.showBottomBar.value = false
                    }
                }
            }

            DiabetixTheme(darkTheme = false) {
                Scaffold(
                    bottomBar = {
                        if (viewModel.showBottomBar.value) {
                            BottomAppBar {
                                Row {
                                    //item nav 1
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                            .clickable {
                                                navController.navigate("homepage")
                                            },
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.ic_homepage),
                                            contentDescription = "Icon Homepage Bottom Bar",
                                            tint = if (viewModel.currentRoute.value == "homepage") GreenNormal else NetralNormal
                                        )
                                        Text(
                                            text = "Home",
                                            style = CustomTheme.typography.p4,
                                            color = if (viewModel.currentRoute.value == "homepage") GreenNormal else NetralNormal
                                        )
                                    }

                                    //item nav 2
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                            .clickable {
                                                navController.navigate("consultation")
                                            },
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.ic_consultation),
                                            contentDescription = "Icon Consultation Bottom Bar",
                                            tint = if (viewModel.currentRoute.value == "consultation") GreenNormal else NetralNormal
                                        )
                                        Text(
                                            text = "Consultation",
                                            style = CustomTheme.typography.p4,
                                            color = if (viewModel.currentRoute.value == "consultation") GreenNormal else NetralNormal
                                        )
                                    }

                                    //item nav 3
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                            .clickable {
                                                navController.navigate("article")
                                            },
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.ic_article),
                                            contentDescription = "Icon Article Bottom Bar",
                                            tint = if (viewModel.currentRoute.value == "article") GreenNormal else NetralNormal
                                        )
                                        Text(
                                            text = "Article",
                                            style = CustomTheme.typography.p4,
                                            color = if (viewModel.currentRoute.value == "article") GreenNormal else NetralNormal
                                        )
                                    }

                                    //item nav 4
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                            .clickable {
                                                navController.navigate("profile")
                                            },
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.ic_profile),
                                            contentDescription = "Icon Profile Bottom Bar",
                                            tint = if (viewModel.currentRoute.value == "profile") GreenNormal else NetralNormal
                                        )
                                        Text(
                                            text = "Profile",
                                            style = CustomTheme.typography.p4,
                                            color = if (viewModel.currentRoute.value == "profile") GreenNormal else NetralNormal
                                        )
                                    }
                                }
                            }
                        }
                    }
                ) {
                    NavHost(navController = navController, startDestination = "add_bmi") {
                        composable("splash") {
                            SplashScreen(navController = navController)
                        }
                        composable("on_boarding") {
                            OnBoardingScreen(navController = navController)
                        }
                        composable("login") {
                            LoginScreen(navController = navController)
                        }
                        composable("register") {
                            RegisterScreen(navController = navController)
                        }
                        composable("verification") {
                            VerificationScreen(navController = navController)
                        }
                        composable("personalization") {
                            PersonalizationScreen(navController = navController)
                        }
                        composable("forgot_password") {
                            ForgotPassword(navController = navController)
                        }
                        composable("new_password") {
                            NewPasswordScreen(navController = navController)
                        }
                        composable("homepage") {
                            HomepageScreen(navController = navController)
                        }
                        composable("consultation") {
                            ConsultationPage(navController = navController)
                        }
                        composable("article") {
                            ArticlePage(navController = navController)
                        }
                        composable("profile") {
                            ProfilePage(navController = navController)
                        }

                        composable("analyze_page") {
                            AnalyzePageScreen(navController = navController)
                        }
                        composable("analyze_result/{imagePath}") { backStackEntry ->
                            val encodedImagePath = backStackEntry.arguments?.getString("imagePath")
                            val imagePath = encodedImagePath?.let { Uri.decode(it) }
                            if (imagePath != null) {
                                AnalyzeResultScreen(
                                    navController = navController,
                                    imagePath = imagePath
                                )
                            }
                        }

                        composable("daily_sugar") {
                            DailySugarScreen(navController = navController)
                        }

                        composable("bmi") {
                            BmiScreen(navController = navController)
                        }
                        composable("add_bmi") {
                            AddBmi(navController = navController)
                        }

                    }

                }
            }
        }
    }
}
