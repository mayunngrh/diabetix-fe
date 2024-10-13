package com.example.diabetix.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal

@Composable
fun GoogleButton(
    modifier: Modifier,
    onClick:()->Unit
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        border = BorderStroke(2.dp, GreenNormal),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp)
    ) {
        AsyncImage(
            modifier = Modifier.size(24.dp),
            model = R.drawable.logo_google,
            contentDescription = "Google Logo Button"
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = "Continue with Google",
            style = CustomTheme.typography.p2,
            fontWeight = FontWeight.SemiBold,
            color = GreenNormal
        )
    }
}