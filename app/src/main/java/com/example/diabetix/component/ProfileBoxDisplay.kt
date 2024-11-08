package com.example.diabetix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightHover

@Composable
fun ProfileBoxDisplay(
    item: String
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(
            GreenLightHover
        ). padding(16.dp), contentAlignment = Alignment.CenterStart){
        Text(modifier = Modifier.fillMaxWidth(), style = CustomTheme.typography.p2, text = item,maxLines = 1)
    }
}