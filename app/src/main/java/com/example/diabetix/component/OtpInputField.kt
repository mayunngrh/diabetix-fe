package com.example.diabetix.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diabetix.ui.theme.MontserratFontFamily

@Composable
fun OTPInputField(otp: String, onOtpChange: (String) -> Unit) {
    val focusRequesters = List(5) { FocusRequester() }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until 5) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(56.dp)
                    .height(64.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(14.dp))
                    .wrapContentSize(Alignment.Center)
            ) {
                BasicTextField(
                    value = otp.getOrNull(i)?.toString() ?: "",
                    onValueChange = { newChar ->
                        if (newChar.length <= 1) {
                            val newOtp = otp.take(i) + newChar + otp.drop(i + 1)
                            onOtpChange(newOtp)

                            if (newChar.isNotEmpty() && i < 4) {
                                focusRequesters[i + 1].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .focusRequester(focusRequesters[i]),
                    textStyle = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions.Default
                )
            }
        }
    }
}