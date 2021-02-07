package meaning.morning.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun wakeupTimeParser(wakeupTime: String): String {
    val inputFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.KOREAN)
    val noMinuteFormat: DateFormat = SimpleDateFormat("매일 HH시 기상", Locale.KOREAN)
    val hasMinuteFormat: DateFormat = SimpleDateFormat("매일 HH시 mm분 기상", Locale.KOREAN)

    return if (wakeupTime.slice(IntRange(3, 4)) == "00") {
        removeZero(wakeupTime, noMinuteFormat.format(inputFormat.parse(wakeupTime)))
    } else {
        removeZero(wakeupTime, hasMinuteFormat.format(inputFormat.parse(wakeupTime)))
    }
}

fun removeZero(wakeupTime: String, output: String): String {
    val startZeroHour = wakeupTime.slice(IntRange(0, 0))
    val changeHour = output.slice(IntRange(3, 4))

    return (when (startZeroHour) {
        "0" -> {
            output.replace(changeHour, Integer.parseInt(changeHour).toString())
        }
        else -> output
    }).toString()
}
