package com.example.diabetix.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLight
import com.example.diabetix.ui.theme.NetralNormal

@Composable
fun RekomendasiAktivitasItem() {
    ElevatedCard(
        modifier = Modifier
            .height(190.dp)
            .width(200.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(GreenLight),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ){
        Column(Modifier.fillMaxSize()) {
            //Foto Rekomendasi Aktivitas
            AsyncImage(
                modifier = Modifier
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = R.drawable.dummy_mission_photo,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            //JUDUL
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.Justify,
                text = "10 Rekomendasi Aktivitas Luar Ruangan",
                style = CustomTheme.typography.p4,
                fontWeight = FontWeight.Bold
            )

            //TANGGAL
            //JUDUL
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                textAlign = TextAlign.Right,
                text = "20 Okt 2024",
                style = CustomTheme.typography.p4,
                color = NetralNormal
            )

        }
    }
    Spacer(modifier = Modifier.width(8.dp))

}