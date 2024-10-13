package com.example.diabetix.presentation.personalization

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diabetix.component.FrequencySelection
import com.example.diabetix.component.GenderSelection
import com.example.diabetix.component.HintNumberField
import com.example.diabetix.component.HintTextField
import com.example.diabetix.component.StepIndicator
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.NetralLightActive
import com.example.diabetix.ui.theme.NetralNormalActive

@Composable
fun PersonalizationScreen(
    navController: NavController
) {
    var currentStep by remember {
        mutableStateOf(1)
    }

    var gender by remember{
        mutableStateOf("")
    }

    var age by remember{
        mutableStateOf("")
    }

    var height by remember{
        mutableStateOf("")
    }

    var weight by remember{
        mutableStateOf("")
    }

    println("Nilai age: " +age)
    println("Nilai height: " +height)
    println("Nilai weight: " +weight)


    println("Nilai gender: " + gender)
    var selectedFrequency by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenNormal)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(
                    Color.White
                )
        ) {
            //BUTTON
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, bottom = 64.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .width(130.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(20.dp),
                        onClick = {
                            if (currentStep > 1) {
                                currentStep--
                            }
                        },
                        border = BorderStroke(2.dp, NetralLightActive)
                    ) {
                        Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = NetralLightActive
                        )
                        Text(
                            modifier = Modifier,
                            text = "Back",
                            style = CustomTheme.typography.p2,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color = NetralLightActive
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        modifier = Modifier
                            .width(130.dp)
                            .height(50.dp), onClick = {
                            if (currentStep < 4) {
                                currentStep++
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GreenNormal,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Next",
                            style = CustomTheme.typography.p2,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Arrow Next"
                        )
                    }
                }
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 48.dp)) {
                    StepIndicator(currentStep = currentStep)

                    Spacer(modifier = Modifier.height(48.dp))
                    when (currentStep) {
                        1 -> {
                            Text(
                                text = "Seberapa tingkat intensitas olahraga kamu?",
                                style = CustomTheme.typography.p1,
                                fontWeight = FontWeight.Bold,
                            )

                            Spacer(modifier = Modifier.height(64.dp))

                            FrequencySelection(
                                selectedFrequency = selectedFrequency,
                                onFrequencySelected = { newFrequency ->
                                    selectedFrequency = newFrequency
                                }
                            )
                        }

                        2->{
                            Text(
                                text = "Apa jenis kelamin kamu?",
                                style = CustomTheme.typography.p1,
                                fontWeight = FontWeight.Bold,
                            )

                            Spacer(modifier = Modifier.height(64.dp))

                            GenderSelection(selectedGender = gender, onGenderSelected = {gender = it})
                        }

                        3->{
                            Text(
                                text = "Berapa umur kamu?",
                                style = CustomTheme.typography.p1,
                                fontWeight = FontWeight.Bold,
                            )

                            Spacer(modifier = Modifier.height(64.dp))

                            HintNumberField(hint = "Masukkan umur anda", onValueChange = {age = it}, value = age)
                        }

                        4->{
                            Text(
                                text = "Masukkan berat dan tinggi badan kamu",
                                style = CustomTheme.typography.p1,
                                fontWeight = FontWeight.Bold,
                            )

                            Spacer(modifier = Modifier.height(64.dp))

                            HintNumberField(hint = "Masukkan tinggi badan anda", onValueChange = {height = it}, value = height)
                            Spacer(modifier = Modifier.height(24.dp))

                            HintNumberField(hint = "Masukkan berat badan anda", onValueChange = {weight = it}, value = weight)

                        }
                    }
                }
            }


        }
    }
}