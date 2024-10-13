package com.example.diabetix.presentation.on_boarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MyButton
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun OnBoardingScreen(
    navController: NavController
) {

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
        AsyncImage(
            model = R.drawable.vector_bg_on_boarding,
            contentDescription = "Background Vector"
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp)
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = R.drawable.photo_on_boarding,
                contentDescription = "OnBoarding Photo Content",
                contentScale = ContentScale.Crop
            )
        }

        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 36.dp)) {
            Text(
                text = "Sehat Setiap Hari Bersama Diabetix",
                style = CustomTheme.typography.h1,
                color = GreenNormal,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Pantau gula darah harianmu guna meminimalisir risiko penyakit diabetes melitus!",
                style = CustomTheme.typography.p3,
                color = GreenNormal,
                fontWeight = FontWeight.Medium,

                )

            Spacer(modifier = Modifier.height(48.dp))

            MyButton(modifier = Modifier, onClick = { /*TODO*/ }, text = "Mulai Sekarang")
        }
    }
}