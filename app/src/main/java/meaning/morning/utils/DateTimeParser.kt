package meaning.morning.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun DateTimeParser(GroupCreatedAt: String): String {
    val input = GroupCreatedAt
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)
    val outputFormat: DateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREAN)

    return outputFormat.format(inputFormat.parse(input))
}
