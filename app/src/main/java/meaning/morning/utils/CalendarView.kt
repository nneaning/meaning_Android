/*
 * Created By: hyooosong
 * on 2021.01.13
 */
package meaning.morning.utils

import java.util.*

class CalendarView {
    private val calendar = Calendar.getInstance()
    private var currentMonthMaxDate = 0
    var data = arrayListOf<Int>()

    init {
        calendar.time = Date()
    }

    fun initCalendar(callback: (Calendar) -> Unit) {
        makeMonthDate(callback)
    }

    private fun makeMonthDate(callback: (Calendar) -> Unit) {
        data.clear()
        calendar.set(Calendar.DATE, 1)
        currentMonthMaxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        makeCurrentMonth(calendar)
        callback(calendar)
    }

    private fun makeCurrentMonth(calendar: Calendar) {
        for (i in 1 until calendar.getActualMaximum(Calendar.DATE)+1)
            data.add(i)
    }
}