package com.example.diabetix.presentation.profile

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.HintTextField
import com.example.diabetix.component.LinearExpChart
import com.example.diabetix.component.MyButton
import com.example.diabetix.component.MyOutlinedButton
import com.example.diabetix.component.ProfileBoxDisplay
import com.example.diabetix.presentation.login.LoginViewModel
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralNormal

@Composable
fun ProfilePage(navController: NavController) {
    val dummyCurrentExp = 250
    val dummyMaxExp = 400

    var name by remember {
        mutableStateOf("Gede Indra Adi Brata")
    }

    var province by remember {
        mutableStateOf("Jawa Timur")
    }

    var city by remember {
        mutableStateOf("Malang")
    }

    var email by remember {
        mutableStateOf("indra@nomail.com")
    }

    var isEdit by remember {
        mutableStateOf(false)
    }

    val viewModel = hiltViewModel<ProfileViewModel>()



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenNormal)
    ) {


        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //PROFILE TEXT AND BACK ICON
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.CenterStart),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = Color.White
                )
                Text(
                    text = "Profile",
                    style = CustomTheme.typography.p1,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 35.dp),
                contentAlignment = Alignment.TopCenter
            ) {


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(
                            Color.White
                        )
                ) {
                    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 100.dp)) {

                        //LEVEL and EXP
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            //LEVEL
                            Text(
                                modifier = Modifier.weight(1f),
                                text = "Level 21",
                                style = CustomTheme.typography.p2,
                                fontWeight = FontWeight.Medium
                            )

                            //EXP
                            Text(
                                text = "250/400",
                                style = CustomTheme.typography.p2,
                                fontWeight = FontWeight.Medium,
                                color = NetralNormal,
                                fontStyle = FontStyle.Italic
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(horizontal = 4.dp),
                                model = R.drawable.ic_exp,
                                contentDescription = "Exp icon"
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        //LEVEL BAR
                        LinearExpChart(currentExp = dummyCurrentExp, maxExp = dummyMaxExp)

                        Spacer(modifier = Modifier.height(24.dp))

                        //ROW NAMA
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.width(80.dp),
                                text = "Nama",
                                style = CustomTheme.typography.p2
                            )

                            if (isEdit) {
                                HintTextField(
                                    hint = "Masukkan Nama Anda",
                                    onValueChange = { name = it },
                                    value = name
                                )
                            } else {
                                ProfileBoxDisplay(item = name)
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))


                        //ROW Email
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.width(80.dp),
                                text = "Email",
                                style = CustomTheme.typography.p2
                            )

                            if (isEdit) {
                                HintTextField(
                                    hint = "Masukkan Kota Asal Anda",
                                    onValueChange = { email = it },
                                    value = email
                                )
                            } else {
                                ProfileBoxDisplay(item = email)
                            }
                        }

                        // BUTTON
                        MyOutlinedButton(
                            modifier = Modifier.padding(vertical = 12.dp),
                            onClick = {
                                isEdit = !isEdit
                            },
                            text = if (isEdit) "Selesai" else "Edit Profile"
                        )
                        MyButton(
                            modifier = Modifier,
                            onClick = {
                                viewModel.logout()
                                navController.navigate("login"){
                                    popUpTo(navController.currentBackStackEntry?.destination?.route ?: "splash") {
                                        inclusive = true
                                    }
                                }
                            },
                            text = "Keluar"
                        )

                        Spacer(modifier = Modifier.height(50.dp))
                    }

                }

                //Photo Profile
                ElevatedCard(
                    modifier = Modifier.size(156.dp),
                    shape = CircleShape,
                    colors = CardDefaults.elevatedCardColors(Color.White)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(CircleShape),
                        model = R.drawable.photo_profile_dummy,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }


    }
}