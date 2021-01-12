/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created By: hyooosong
 * on 2021.01.13
 */
package meaning.morning.presentation.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
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
import meaning.morning.presentation.adapter.home.CalendarAdapter
import meaning.morning.presentation.adapter.home.HomeCardAdapter
import meaning.morning.data.HomeCardData
import meaning.morning.presentation.home.card.CardPromiseActivity
import meaning.morning.presentation.home.card.CardReadingActivity
import meaning.morning.presentation.home.card.CardTimeStampActivity
import meaning.morning.presentation.home.card.CardWriteDiaryActivity
import meaning.morning.utils.HomeCardItemDecoreation
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var calendaradapter: CalendarAdapter
    var isCardView: Boolean = true
    private var mediumAnimationDuration: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

        setCardListRcv()

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

    private fun setCardListRcv() {
        val homeCardData = mutableListOf<HomeCardData>()

        val homeCardAdapter by lazy {
            HomeCardAdapter { position: Int, cardItem: HomeCardData ->
                binding.layoutCardview.smoothScrollToPosition(position)
            }
        }


        binding.layoutCardview.apply {
            initialize(homeCardAdapter)
            addItemDecoration(HomeCardItemDecoreation(requireContext()))
        }

        homeCardData.apply {
            add(
                HomeCardData(R.drawable.missioncard_1)
            )
            add(
                HomeCardData(R.drawable.missioncard_2)
            )
            add(
                HomeCardData(R.drawable.missioncard_3)
            )
            add(
                HomeCardData(R.drawable.missioncard_4)
            )

        }
        homeCardAdapter.submitData(homeCardData)

        homeCardAdapter.setItemClickListener(object : HomeCardAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                if (position == 0) {
                    sendMission1()
                } else if (position == 1) {
                    sendMission2()
                } else if (position == 2) {
                    sendMission3()
                } else if (position == 3) {
                    sendMission4()
                }
            }
        })
    }

    private fun sendMission1() {
        val intent = Intent(requireContext(), CardTimeStampActivity::class.java)
        startActivity(intent)
    }

    private fun sendMission2() {
        val intent = Intent(requireContext(), CardPromiseActivity::class.java)
        startActivity(intent)
    }

    private fun sendMission3() {
        val intent = Intent(requireContext(), CardWriteDiaryActivity::class.java)
        startActivity(intent)
    }

    private fun sendMission4() {
        val intent = Intent(requireContext(), CardReadingActivity::class.java)
        startActivity(intent)
    }
}