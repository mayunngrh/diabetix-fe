import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.diabetix.ui.theme.GreenLightHover
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.R
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.RedNormal

@Composable
fun SemiCircularChart(
    currentValue: Int,
    maxValue: Int,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(220.dp)
    ) {
        var startAngle = 155f
        var sweepAngle = 225f
        var progressColor: Color = GreenNormal
        var backgroundColor: Color = GreenLightHover

        if (currentValue > maxValue) {
            startAngle = 155f
            sweepAngle = 225f
            progressColor = RedNormal
        } else {

            startAngle = 155f
            sweepAngle = 225f * (currentValue / maxValue.toFloat())
        }


        // Canvas for drawing the semicircle
        Canvas(modifier = Modifier.size(175.dp)) {
            // Background arc (full semicircle)
            drawArc(
                color = backgroundColor,
                startAngle = startAngle,
                sweepAngle = 225f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round)
            )

            // Progress arc
            drawArc(
                color = progressColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        // Centered text
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = "$currentValue gr",
            style = CustomTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            color = progressColor
        )

        // Bottom labels and icon
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 135.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "0gr",
                    color = progressColor,
                    style = CustomTheme.typography.p1,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(R.drawable.ic_chart),
                    contentDescription = "",
                    tint = progressColor
                )
                Text(
                    text = "${maxValue}gr",
                    color = progressColor,
                    style = CustomTheme.typography.p1,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
