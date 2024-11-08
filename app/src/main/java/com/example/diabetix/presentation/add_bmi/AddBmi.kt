package com.example.diabetix.presentation.add_bmi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.HintTextField
import com.example.diabetix.component.MyButton
import com.example.diabetix.presentation.bmi.DetailBmiScreen
import com.example.diabetix.presentation.bmi.RiwayatBmiScreen
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.MontserratFontFamily
import com.example.diabetix.ui.theme.NetralNormal

@Composable
fun AddBmi(navController: NavController) {

    var height by remember{
        mutableStateOf("")
    }

    var weight by remember{
        mutableStateOf("")
    }


    var color1 by remember{
        mutableStateOf(NetralNormal)
    }

    var color2 by remember{
        mutableStateOf(NetralNormal)
    }

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
            text = "BMI",
            style = CustomTheme.typography.h2,
            fontWeight = FontWeight.Bold,
            color = GreenNormal
        )
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(modifier = Modifier.size(250.dp), model = R.drawable.vector_add_bmi, contentDescription = "")
        Spacer(modifier = Modifier.height(36.dp))


        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Masukkan Data Kamu",
            style = CustomTheme.typography.p2,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        //HINT BOX TINGGI
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            if (height.isEmpty()) {
                color1 = NetralNormal
                Text(
                    modifier= Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                    text = "Tinggi Badan (cm)",
                    style = CustomTheme.typography.p2,
                    color = NetralNormal
                )
            } else{
                color1 = GreenNormal
            }

            BasicTextField(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 24.dp),
                value = height,
                onValueChange = {height = it},
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = GreenNormal
                ),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        //HINT BOX BERAT
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .border(
                    width = 1.dp,
                    color = color2,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            if (weight.isEmpty()) {
                color2 = NetralNormal
                Text(
                    modifier= Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                    text = "Berat Badan (kg)",
                    style = CustomTheme.typography.p2,
                    color = NetralNormal
                )
            } else{
                color2 = GreenNormal
            }

            BasicTextField(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 24.dp),
                value = weight,
                onValueChange = {weight = it},
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = GreenNormal
                ),
            )
        }


        Spacer(modifier = Modifier.height(36.dp))
        MyButton(modifier = Modifier, onClick = {
                                                //SAVE THE BMI
        }, text = "Simpan")
        
    }
}