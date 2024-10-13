package com.example.diabetix.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.MontserratFontFamily
import com.example.diabetix.ui.theme.NetralNormal


@Composable
fun HintPasswordField(
    hint: String,
    onValueChange: (String) -> Unit,
    value: String
) {
    var color by remember {
        mutableStateOf(NetralNormal)
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    var icon by remember {
        mutableStateOf(R.drawable.hide_password)
    }
    if (showPassword) {
        icon = R.drawable.show_password
    } else {
        icon = R.drawable.hide_password
    }



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(
                width = 1.dp,
                color = color,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        if (value.isEmpty()) {
            color = NetralNormal
            Text(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 24.dp),
                text = hint,
                style = CustomTheme.typography.p2,
                color = NetralNormal,

            )

        } else {
            color = GreenNormal
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(vertical = 20.dp, horizontal = 24.dp),
        ) {
            BasicTextField(
                modifier = Modifier
                    .height(60.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                value = value,
                onValueChange = onValueChange,
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = GreenNormal
                ),
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .clickable { showPassword = !showPassword },
                model = icon, contentDescription = "Icon Show Password"
            )
        }
    }
}