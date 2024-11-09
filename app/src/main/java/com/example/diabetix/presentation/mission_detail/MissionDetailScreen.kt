package com.example.diabetix.presentation.mission_detail

import android.widget.Toast
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MyButton
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import dev.jeziellago.compose.markdowntext.MarkdownText


@Composable
fun MissionDetailScreen(navController: NavController) {
    var isDone by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val markDownText =
        """Jogging selama 15 menit adalah aktivitas ringan yang dapat dilakukan oleh siapa saja untuk meningkatkan kebugaran tubuh. Jogging dapat dimulai dengan melakukan:

1. **Mulai jogging ringan** selama 5 menit
2. **Tingkatkan kecepatan** selama 7 menit
3. **Akhiri dengan pendinginan** selama 3 menit

## Manfaat

1. **Meningkatkan Kesehatan Jantung**: Membantu melancarkan sirkulasi darah dan menguatkan otot jantung.
2. **Membakar Kalori**: Membantu membakar kalori secara efektif, mendukung penurunan berat badan.
3. **Meningkatkan Stamina**: Melatih daya tahan tubuh sehingga lebih bertenaga dalam beraktivitas sehari-hari.


    """.trimIndent()

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
                text = "Detail Misi",
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
                    .clip(RoundedCornerShape(24.dp)),
                model = R.drawable.dummy_photo_mission,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))


            //ROW EXP and CALORIES
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //ICON EXP
                AsyncImage(
                    modifier = Modifier.size(16.dp),
                    model = R.drawable.ic_exp,
                    contentDescription = "ICON EXP"
                )

                //JUMLAH EXP
                Text(
                    modifier = Modifier.padding(start = 4.dp, end = 16.dp),
                    text = "150 xp",
                    style = CustomTheme.typography.p4,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))
                //JUMLAH CALORIE
                Text(
                    modifier = Modifier.padding(start = 4.dp, end = 16.dp),
                    text = "1000 kkal",
                    style = CustomTheme.typography.p4,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }



            Spacer(modifier = Modifier.height(24.dp))

            //JUDUL
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Jogging 15 Menit",
                style = CustomTheme.typography.p1,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            MarkdownText(
                modifier = Modifier.fillMaxSize(), markdown = markDownText,
                style = CustomTheme.typography.p4
            )

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    colors = CheckboxDefaults.colors(GreenNormal),
                    checked = isDone,
                    onCheckedChange = { isDone = !isDone })

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Saya benar benar telah melakukan kegiatan ini",
                    style = CustomTheme.typography.p4,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            MyButton(modifier = Modifier, onClick = {
                if (isDone) {
                    //DO THE THING
                } else{
                    Toast.makeText(context, "Silahkan isi kotak centang terlebih dahulu!", Toast.LENGTH_SHORT).show()
                }

            }, text = "Selesaikan Misi")
            Spacer(modifier = Modifier.height(48.dp))


        }
    }
}