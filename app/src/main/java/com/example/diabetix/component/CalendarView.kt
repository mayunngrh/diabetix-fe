import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import com.example.diabetix.ui.theme.RedLightActive
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun CalendarView(
    startDate: String,
    endDate:String,
) {
    val start = startDate.substringBefore("T")
    val end = endDate.substringBefore("T")
    val endDate = LocalDate.parse(end)
    val startDate = LocalDate.parse(start)

Column {

    //TANGGAL
    Text(
        text = "${startDate.dayOfMonth} ${startDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${startDate.year} - " +
                "${endDate.dayOfMonth} ${endDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${endDate.year}",
        style = CustomTheme.typography.p2,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )



    //KALENDER
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .background(Color(0xFFD3F2E3), shape = RoundedCornerShape(36.dp))
            .padding(24.dp)
    ) {


        Spacer(modifier = Modifier.height(12.dp))

        // Display month and year
        Text(
            text = "${endDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${endDate.year}",
            style = CustomTheme.typography.p3,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))
        // Days of the week
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min").forEach { day ->
                Text(text = day, fontSize = 14.sp, color = Color(0xFF00796B), fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dates in the calendar
        val daysInMonth = endDate.lengthOfMonth()
        val firstDayOfMonth = endDate.withDayOfMonth(1).dayOfWeek.value % 7

        // Generate calendar rows
        for (week in 0..5) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (dayOfWeek in 0..6) {
                    val day = week * 7 + dayOfWeek - firstDayOfMonth + 1
                    if (day in 1..daysInMonth) {
                        val date = endDate.withDayOfMonth(day)
                        val isInRange = date in startDate..endDate

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    color = if (isInRange) RedLightActive else Color.Transparent,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day.toString(),
                                color = if (isInRange) GreenNormal else GreenNormal,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    } else {
                    }
                }
            }
        }
    }
}
}
