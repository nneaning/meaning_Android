/*
 * Created By: hyooosong
 * on 2021.01.13
 */
package meaning.morning.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.databinding.ItemCalendarDateBinding
import meaning.morning.utils.CalendarView

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.VHolder>() {
    private val calendar = CalendarView()

    init{
        calendar.initCalendar {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemCalendarDateBinding
            .inflate(layoutInflater, parent, false)
            .let { VHolder(it) }
    }
    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.date.text=calendar.data[position].toString()
    }

    override fun getItemCount(): Int = calendar.data.size

    class VHolder(binding: ItemCalendarDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val date = binding.textviewCalendarDate
    }
}