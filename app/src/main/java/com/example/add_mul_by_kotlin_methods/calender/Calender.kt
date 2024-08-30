package com.example.add_mul_by_kotlin_methods.calender

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.Locale

data class Holiday(val month: Int, val day: Int, val title: String, val description: String)

val publicHolidays = listOf(
    Holiday(
        1,
        2,
        "Mannam Jayanthi",
        "Mannam Jayanthi is celebrated annually to honor the life and legacy of Dr. P. Subrahmanyam Mannam, who was a key figure in promoting education and social reforms in Kerala. " +
                "His efforts in advocating for the rights of marginalized communities and his work towards improving educational opportunities have left a lasting impact on the society."
    ),
    Holiday(
        1,
        26,
        "Republic Day",
        "Republic Day is celebrated annually in India on January 26th. It marks the date in 1950 when the Constitution of India came into effect, transforming the country into a sovereign republic. This day is a national holiday and is celebrated with great fervor across the country."
    ),
    Holiday(
        3,
        8,
        "Maha Shivarathri",
        "Maha Shivaratri is a major Hindu festival dedicated to Lord Shiva, one of the principal deities in Hinduism. The festival is celebrated annually on the 13th or 14th night of the new moon during the Hindu month of Phalguna (February-March). The name \"Maha Shivaratri\" translates to \"the Great Night of Shiva.\""
    ),
    Holiday(
        3,
        28,
        "Maunder Thursday",
        "Maundy Thursday, also known as Holy Thursday, Covenant Thursday, or Sheer Thursday, is a significant Christian observance that commemorates the events of the Last Supper, which took place on the Thursday before Easter Sunday. This day marks the beginning of the Easter Triduum, the three-day period leading up to Easter."
    ),
    Holiday(
        3,
        31,
        "Easter",
        "Easter is one of the most important and joyous holidays in the Christian calendar, celebrating the resurrection of Jesus Christ from the dead. It is the culmination of the Easter Triduum, which includes Maundy Thursday, Good Friday, and Holy Saturday, and marks the end of Lent, a season of penance and fasting."
    ),
    Holiday(
        4,
        10,
        "Eid al-Fitr",
        "Eid al-Fitr falls on the first day of Shawwal, the tenth month of the Islamic lunar calendar, following the sighting of the new moon. The exact date varies depending on the sighting of the moon, and it can differ between countries and communities."
    ),
    Holiday(
        4,
        14,
        "Ambedkar Jayanti",
        "Ambedkar Jayanti is an annual celebration held on April 14th in honor of Dr. B.R. Ambedkar, a prominent social reformer, economist, and the principal architect of the Indian Constitution. This day is observed to commemorate his contributions to social justice, equality, and the fight against caste discrimination."
    ),
    Holiday(
        4,
        14,
        "Vishu",
        "Vishu is celebrated on the first day of the Malayalam month of Medam, which typically corresponds to April 14th or 15th. The festival signifies the start of a new year in the traditional Malayalam calendar and is a time for family gatherings, religious observances, and festive activities."
    ),
    Holiday(
        5,
        1,
        "Labour Day",
        "Labour Day is observed on May 1st and is celebrated in many countries, often as a day of reflection, activism, and celebration of workers' achievements and struggles. The day highlights the importance of labor rights, fair working conditions, and the history of the labor movement."
    ),
    Holiday(
        6,
        17,
        "Eid al-Adha",
        "Eid al-Adha, also known as the Festival of Sacrifice, is one of the two major Islamic holidays celebrated by Muslims worldwide, the other being Eid al-Fitr. It commemorates the willingness of the Prophet Ibrahim (Abraham) to sacrifice his son as an act of obedience to Allah's command. Eid al-Adha occurs during the Islamic lunar month of Dhu al-Hijjah, specifically on the 10th day, and marks the end of the Hajj pilgrimage to Mecca."
    ),
    Holiday(
        7,
        16,
        "Ashura",
        "Ashura is an important day in the Islamic calendar observed on the 10th day of Muharram, the first month of the Islamic lunar calendar. Its significance varies among different Islamic traditions, making it a day of both religious reflection and observance."
    ),
    Holiday(
        8,
        3,
        "Karkkidaka vavu",
        "Karkkidaka Vavu is a significant day in the Malayalam calendar, observed on the new moon day (Amavasya) in the month of Karkkidakam (July-August). It is primarily celebrated in the Indian state of Kerala and among the Malayali diaspora. The day holds particular importance for performing rites and rituals for deceased ancestors."
    ),
    Holiday(
        8,
        15,
        "Independence Day",
        "Independence Day is a significant national holiday celebrated in various countries to commemorate the anniversary of their independence or liberation from colonial rule or occupation. Each country may have its own specific date and historical context for its Independence Day, but the common theme is the celebration of freedom, sovereignty, and national pride."
    ),
    Holiday(
        8,
        20,
        "Sir Narayana Guru Jayanti",
        "Sir Narayana Guru Jayanti is a significant celebration in Kerala, India, honoring the birth anniversary of Sri Narayana Guru (1856–1928), a prominent social reformer and spiritual leader. His teachings and contributions were instrumental in advancing social justice, educational reform, and religious harmony in Kerala."
    ),
    Holiday(
        8,
        26,
        "Krishna Jhanmashtami",
        "Krishna Janmashtami, also known simply as Janmashtami, is a major Hindu festival celebrating the birth of Lord Krishna, the eighth avatar of Vishnu. The festival typically falls in August, on the 8th day of the Krishna Paksha (dark fortnight) of the month of Bhadrapada according to the Hindu lunar calendar."
    ),
    Holiday(
        8,
        28,
        "Ayyankali Jayanthi",
        "Ayyankali Jayanthi is a significant day in Kerala, India, commemorating the birth anniversary of Ayyankali (1863–1941), a prominent social reformer and leader who dedicated his life to improving the social and economic conditions of the marginalized communities in Kerala. His work was instrumental in the struggle against caste discrimination and for the upliftment of the lower castes."
    ),
    Holiday(
        9,
        15,
        "Mawlid",
        "Mawlid al-Nabi, often simply referred to as Mawlid, is an important Islamic observance that commemorates the birth of the Prophet Muhammad, the founder of Islam. The celebration of Mawlid varies in its practices and significance across different Muslim communities, but it is generally a time of joy, reflection, and devotion."
    ),
    Holiday(
        9,
        16,
        "Onam",
        "Onam is a multi-day festival that usually takes place in August or September, during the month of Chingam in the Malayalam calendar. It is celebrated with a blend of traditional rituals, cultural performances, and feasts, reflecting the rich heritage and communal spirit of Kerala."
    ),
    Holiday(
        9,
        17,
        "Onam",
        "Onam is a multi-day festival that usually takes place in August or September, during the month of Chingam in the Malayalam calendar. It is celebrated with a blend of traditional rituals, cultural performances, and feasts, reflecting the rich heritage and communal spirit of Kerala."
    ),
    Holiday(
        9,
        21,
        "Sree narayana Guru Samadhi",
        "Sree Narayana Guru Samadhi refers to the memorial and final resting place of Sree Narayana Guru, a revered social reformer, philosopher, and spiritual leader from Kerala, India. The term \"Samadhi\" denotes the place where a revered spiritual figure is laid to rest and where followers gather to pay their respects and reflect on their teachings."
    ),
    Holiday(
        10,
        2,
        "Gandhi jayathi",
        "Gandhi Jayanti, also known as Mahatma Gandhi Jayanti, is a significant national holiday in India that commemorates the birth anniversary of Mahatma Gandhi, one of the most influential leaders in the Indian independence movement and a global symbol of nonviolent resistance."
    ),
    Holiday(
        10,
        12,
        "Maha Navami",
        "Maha Navami is a significant day in the Hindu festival of Navaratri, which is dedicated to the worship of the goddess Durga. Navaratri is a nine-night festival celebrated in honor of the divine feminine energy, and Maha Navami is the culmination of these nine nights. It is celebrated on the ninth day of the Navaratri festival and is particularly notable for its religious and cultural importance."
    ),
    Holiday(
        10,
        31,
        "Diwali",
        "Diwali, also known as Deepavali, is one of the most widely celebrated and significant festivals in India and among Indian communities worldwide. Often referred to as the \"Festival of Lights,\" Diwali symbolizes the victory of light over darkness and good over evil. The festival spans five days and is marked by various customs, rituals, and celebrations."
    ),
    Holiday(
        12,
        25,
        "Christmas Day",
        "Christmas Day is celebrated on December 25th and marks the culmination of the Christmas season, which begins with Advent and includes various celebrations leading up to the day itself."
    ),

    )

@Composable
fun CalendarApp() {
    val calendar = Calendar.getInstance()
    var selectedYear by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }

    AllYearCalender(year = selectedYear, onYearSelected = { year ->
        selectedYear = year
    })
}

@Composable
fun AllYearCalender(year: Int, onYearSelected: (Int) -> Unit) {
    val startYear = 1990
    val endYear = 9999
    val yearsRange = startYear..endYear
    var showYearDialog by remember { mutableStateOf(false) }
    var selectedHoliday by remember { mutableStateOf<Holiday?>(null) }


    LazyColumn {
        item {
            CalenderHeader()
        }
        items((0..11).toList()) {
            Calender(year = year,
                month = it,
                onHolidayClick = { holiday ->
                    selectedHoliday = holiday
                },
                onYearClick = {
                    showYearDialog = true
                }
            )
            HorizontalDivider(
                color = if (it != 11) Color.DarkGray else Color.Transparent,
                thickness = 1.dp,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
    }

    yearsRange.forEach { year ->
        println(year)
    }

    selectedHoliday?.let {
        AlertDialog(
            title = {
                Text(
                    text = it.title,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Text(text = it.description)
            },
            onDismissRequest = {
                selectedHoliday = null
            }, confirmButton = {
                TextButton(onClick = { selectedHoliday = null }) {
                    Text(text = "Ok")
                }
            })
    }

    if (showYearDialog) {
        AlertDialog(
            title = {
                Text(
                    "Pick Year",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            text = {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize()
                ) {
                    val years = yearsRange.toList()
                         items(years){year->
                             Text(
                                 text = year.toString(),
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .clickable {
                                         onYearSelected(year)
                                         showYearDialog = false
                                     }
                                     .padding(16.dp),
                                 style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                 textAlign = TextAlign.Center
                             )
                         }
                }
            },
            onDismissRequest = {
                showYearDialog = false
            }, confirmButton = {
                TextButton(onClick = {
                    showYearDialog = false
                }) {
                    Text("Ok")
                }
            })
    }


}


@Composable
fun Calender(year: Int, month: Int, onHolidayClick: (Holiday) -> Unit, onYearClick: () -> Unit) {

    val calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, 1)
    }

    val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        Row(modifier = Modifier.clickable {
            onYearClick()
        }) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "calenderHeader",
                tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "${
                    calendar.getDisplayName(
                        Calendar.MONTH,
                        Calendar.LONG,
                        Locale.getDefault()
                    )
                } $year",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // Days of the week headers
        Row(modifier = Modifier.fillMaxWidth()) {
            daysOfWeek.forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = if (day == "Sun") Color.Red
                    else if (day == "Sat") Color.Blue
                    else MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        // Calculate days rows
        val totalCells = daysInMonth + (firstDayOfWeek - 1)
        println("TotalCells:--$totalCells")
        val rows = (totalCells + 6) / 7

        Column {
            for (rowIndex in 0 until rows) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (colIndex in 0 until 7) {
                        val day = rowIndex * 7 + colIndex - (firstDayOfWeek - 2)
                        if (day in 1..daysInMonth) {
                            val isHoliday =
                                publicHolidays.any { it.month == month + 1 && it.day == day }
                            val holiday =
                                publicHolidays.find { it.month == month + 1 && it.day == day }
                            val circleColor = /*if (isHoliday) Color.Red else*/ Color.Transparent
                            val color =
                                /*if (isHoliday) Color.White else*/ MaterialTheme.colorScheme.onBackground
                            Text(
                                text = "$day",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                                    /*.drawBehind {
                                        drawCircle(
                                            color = circleColor,
                                            radius = this.size.component2()
                                        )
                                    }*/
                                    .clickable {
                                        holiday?.let {
                                            onHolidayClick(it)
                                        }
                                    },
                                style = MaterialTheme.typography.bodyLarge,
                                color = color,
                                fontWeight = /*if (isHoliday) FontWeight.Bold else*/ FontWeight.Normal
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f)) // Spacer for empty cells
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp)) // Space between weeks
            }
        }
    }

    /* Column {
         repeat(rows) { rowIndex ->
             Row(modifier = Modifier.fillMaxWidth()) {
                 for (colIndex in 0 until 7) {
                     val day = rowIndex * 7 + colIndex - (firstDayOfWeek - 1)
                     if (day in 1..daysInMonth) {
                         Text(
                             text = "$day",
                             modifier = Modifier
                                 .weight(1f)
                                 .padding(4.dp),
                             style = MaterialTheme.typography.bodyLarge,
                             color = MaterialTheme.colorScheme.onBackground
                         )
                     } else {
                         Spacer(modifier = Modifier.weight(1f))
                     }
                 }
             }
             Spacer(modifier = Modifier.height(4.dp))
         }

         // Add last row with extra cells if they exist
         if (extraCells > 0) {
             Row(modifier = Modifier.fillMaxWidth()) {
                 for (colIndex in 0 until 7) {
                     val day = rows * 7 + colIndex - (firstDayOfWeek - 1)
                     if (colIndex >= extraCells) {
                         Spacer(modifier = Modifier.weight(1f))
                     } else if (day in 1..daysInMonth) {
                         Text(
                             text = "$day",
                             modifier = Modifier
                                 .weight(1f)
                                 .padding(4.dp),
                             style = MaterialTheme.typography.bodyLarge,
                             color = MaterialTheme.colorScheme.onBackground
                         )
                     }
                 }
             }
         }
     }
 }*/
}


@Preview(showBackground = true)
@Composable
fun PreviewCalendarView() {
    CalendarApp()
}