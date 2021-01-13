package meaning.morning.network.response

data class CalendarResponse(
    val calendar: List<Calendar>,
    val successDays: Int
) {
    data class Calendar(
        val dateTime: String,
        val status: Int
    )
}