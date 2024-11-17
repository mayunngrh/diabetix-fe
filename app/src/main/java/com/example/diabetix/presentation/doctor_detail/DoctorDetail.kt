package com.example.diabetix.presentation.doctor_detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MyButton
import com.example.diabetix.data.Missions
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun DoctorDetail(
) {

    val context = LocalContext.current
    val markDownText =
        "Dr. Riska Subandono adalah seorang dokter spesialis penyakit dalam yang berpraktik di Rumah Sakit Husada. Dengan keahlian dalam menangani berbagai masalah kesehatan yang berkaitan dengan organ-organ internal, Dr. Riska berdedikasi untuk memberikan perawatan medis yang menyeluruh dan terfokus pada kebutuhan pasien. Berbekal pengalaman dan pengetahuan mendalam di bidang penyakit dalam, beliau senantiasa berupaya memberikan diagnosis yang akurat dan perawatan yang efektif untuk membantu pasien mencapai pemulihan yang optimal.".trimIndent()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        //HEADER SECTION
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.BottomStart),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
                tint = GreenNormal
            )
            Text(
                modifier = Modifier,
                text = "Konsultasi",
                style = CustomTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                color = GreenNormal
            )
        }

        Column(modifier = Modifier.padding(24.dp)) {

            //FOTO MISI AKTIVITAS
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = R.drawable.dummy_doctor_photo,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))



            Spacer(modifier = Modifier.height(24.dp))

            //JUDUL
            //BOX NAMA MAKANAN
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(GreenLightHover)
            ) {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFB0E4D3))
                        .align(Alignment.CenterEnd),
                    contentAlignment = Alignment.Center
                ) {
                    //NAMA DOKTER
                    Text(
                        text = "Dr. Riska Subandono",
                        style = CustomTheme.typography.p3,
                        fontWeight = FontWeight.Bold,
                        color = GreenNormal
                    )
                }
                Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = "Nama Dokter",
                        style = CustomTheme.typography.p4
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            //DESKRIPSI
            MarkdownText(
                modifier = Modifier.fillMaxSize(), markdown = markDownText,
                style = CustomTheme.typography.p4
            )

            Spacer(modifier = Modifier.height(36.dp))


            Spacer(modifier = Modifier.height(24.dp))
            MyButton(modifier = Modifier, onClick = {
                    Toast.makeText(
                        context,
                        "Silahkan isi kotak centang terlebih dahulu!",
                        Toast.LENGTH_SHORT
                    ).show()
            }, text = "Reservasi")
            Spacer(modifier = Modifier.height(48.dp))


        }
    }
}