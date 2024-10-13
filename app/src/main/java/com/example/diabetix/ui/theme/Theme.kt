package com.example.diabetix.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.diabetix.R

@Immutable
data class CustomTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val p1: TextStyle,
    val p2: TextStyle,
    val p3: TextStyle,
    val p4: TextStyle
)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val MontserratFontFamily = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semi_bold, FontWeight.SemiBold)
)

val LocalTypography = staticCompositionLocalOf {
    CustomTypography(
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        p1 = TextStyle.Default,
        p2 = TextStyle.Default,
        p3 = TextStyle.Default,
        p4 = TextStyle.Default
    )
}
@Composable
fun DiabetixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }



    val customTypography = CustomTypography(
        h1 = TextStyle(
            fontSize = 36.sp,
            fontFamily = MontserratFontFamily
        ),
        h2 = TextStyle(
            fontSize = 24.sp,
            fontFamily = MontserratFontFamily
        ),
        p1 = TextStyle(
            fontSize = 18.sp,
            fontFamily = MontserratFontFamily
        ),
        p2 = TextStyle(
            fontSize = 16.sp,
            fontFamily = MontserratFontFamily
        ),
        p3 = TextStyle(
            fontSize = 14.sp,
            fontFamily = MontserratFontFamily
        ),
        p4 = TextStyle(
            fontSize = 12.sp,
            fontFamily = MontserratFontFamily
        )
    )

    CompositionLocalProvider(
        LocalTypography provides customTypography,
        content = content
    )
}

object CustomTheme{
    val typography:CustomTypography
        @Composable get() = LocalTypography.current
}