package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightActive
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun KategoriBMI() {
    AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        model = R.drawable.ic_kategori_bmi,
        contentDescription = "BMI CHART",
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.height(24.dp))

    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Left,
        text = "Kategori BMI",
        style = CustomTheme.typography.p2,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(12.dp))

    //KATEGORI 1
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(GreenLightActive)
            .padding(12.dp))

        Spacer(modifier = Modifier.width(24.dp))
        Text(
            modifier = Modifier.width(80.dp),
            textAlign = TextAlign.Left,
            text = "BMI >18.5",
            style = CustomTheme.typography.p3,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(24.dp))

        Text(
            modifier = Modifier,
            textAlign = TextAlign.Left,
            text = "Underweight",
            style = CustomTheme.typography.p3,
        )
    }
    Spacer(modifier = Modifier.height(12.dp))

    //KATEGORI 2
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(GreenNormal)
            .padding(12.dp))

        Spacer(modifier = Modifier.width(24.dp))
        Text(
            modifier = Modifier.width(80.dp),
            textAlign = TextAlign.Left,
            text = "18.5 - 24.9",
            style = CustomTheme.typography.p3,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(24.dp))

        Text(
            modifier = Modifier,
            textAlign = TextAlign.Left,
            text = "Normal weight",
            style = CustomTheme.typography.p3,
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    //KATEGORI 3
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFFFDB022))
            .padding(12.dp))

        Spacer(modifier = Modifier.width(24.dp))
        Text(
            modifier = Modifier.width(80.dp),
            textAlign = TextAlign.Left,
            text = "25.0 - 29.9",
            style = CustomTheme.typography.p3,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(24.dp))

        Text(
            modifier = Modifier,
            textAlign = TextAlign.Left,
            text = "Overweight",
            style = CustomTheme.typography.p3,
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    //KATEGORI 4
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFFCA8D1B))
            .padding(12.dp))

        Spacer(modifier = Modifier.width(24.dp))
        Text(
            modifier = Modifier.width(80.dp),
            textAlign = TextAlign.Left,
            text = "30.0 - 34.9",
            style = CustomTheme.typography.p3,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(24.dp))

        Text(
            modifier = Modifier,
            textAlign = TextAlign.Left,
            text = "Obesity Class I",
            style = CustomTheme.typography.p3,
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    //KATEGORI 5
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFFD83D32))
            .padding(12.dp))

        Spacer(modifier = Modifier.width(24.dp))
        Text(
            modifier = Modifier.width(80.dp),
            textAlign = TextAlign.Left,
            text = "35.0 - 39.9",
            style = CustomTheme.typography.p3,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(24.dp))

        Text(
            modifier = Modifier,
            textAlign = TextAlign.Left,
            text = "Obesity Class II",
            style = CustomTheme.typography.p3,
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    //KATEGORI 5
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFF6C1F19))
            .padding(12.dp))

        Spacer(modifier = Modifier.width(24.dp))
        Text(
            modifier = Modifier.width(80.dp),
            textAlign = TextAlign.Left,
            text = "BMI >39.9",
            style = CustomTheme.typography.p3,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(24.dp))

        Text(
            modifier = Modifier,
            textAlign = TextAlign.Left,
            text = "Obesity Class III",
            style = CustomTheme.typography.p3,
        )
    }
}