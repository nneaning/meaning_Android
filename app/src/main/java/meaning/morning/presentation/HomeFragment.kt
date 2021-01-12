/*
 * Created By: hyooosong
 * on 2021.01.13
 */
package meaning.morning.presentation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.databinding.FragmentHomeBinding
import meaning.morning.presentation.adapter.CalendarAdapter
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var calendaradapter: CalendarAdapter
    var isCardView: Boolean = true
    private var mediumAnimationDuration: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        showCurrentMonth(Calendar.getInstance())
        mediumAnimationDuration = resources.getInteger(android.R.integer.config_mediumAnimTime)

        binding.tvDate.setOnClickListener {
            toggleHomeView(binding.tvDate)
        }
    }

    private fun initView() {
        calendaradapter = CalendarAdapter()
        binding.rcvCalendarDate.apply {
            adapter = calendaradapter
        }
        binding.layoutHomeCardView.apply {
            animate()
                .alpha(0f)
                .setDuration(mediumAnimationDuration.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        binding.layoutHomeCardView.visibility = View.GONE
                    }
                })
        }

    }

    private fun showCurrentMonth(calendar: Calendar) {
        val currentMonth = (calendar.get(Calendar.MONTH)) + 1
        binding.textviewCurrentMonth.text = "${currentMonth}월"
    }

    private fun toggleHomeView(dateText: TextView) {
        if (isCardView) {
            dateText.setBackgroundResource(R.drawable.main_calendar_button)
            dateText.setTextColor(Color.parseColor("#F6FAFB"))
            calendarViewVisibility()
            isCardView = false
            return
        }
        dateText.setBackgroundResource(R.drawable.main_date_button)
        dateText.setTextColor(Color.parseColor("#17234D"))
        cardViewVisibility()
        isCardView = true
    }

    private fun cardViewVisibility() {
        crossfade(binding.layoutHomeCardView, binding.layoutHomeCalendarView)
    }

    private fun calendarViewVisibility() {
        crossfade(binding.layoutHomeCalendarView, binding.layoutHomeCardView)
    }

    private fun crossfade(visibleView: View, invisibleView: View) {
        visibleView.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .setDuration(mediumAnimationDuration.toLong())
                .setListener(null)
        }

        invisibleView.animate()
            .alpha(0f)
            .setDuration(mediumAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    invisibleView.visibility = View.GONE
                }
            })
    }
}