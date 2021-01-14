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
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.data.HomeCardData
import meaning.morning.databinding.FragmentHomeBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.MeaningService.Companion.meaningToken
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.CalendarResponse
import meaning.morning.presentation.adapter.home.CalendarAdapter
import meaning.morning.presentation.adapter.home.HomeCardAdapter
import meaning.morning.presentation.home.card.CardPromiseActivity
import meaning.morning.presentation.home.card.CardReadingActivity
import meaning.morning.presentation.home.card.CardTimeStampActivity
import meaning.morning.presentation.home.card.CardWriteDiaryActivity
import meaning.morning.presentation.home.feed.MyFeedMainActivity
import meaning.morning.utils.HomeCardItemDecoreation
import meaning.morning.utils.enqueueListener
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var calendaradapter: CalendarAdapter
    private var isCardView: Boolean = true
    private var isFirstAnim: Boolean = true
    private var mediumAnimationDuration: Int = 0
    var starData = arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediumAnimationDuration = resources.getInteger(android.R.integer.config_mediumAnimTime)
        initView()

        showCurrentMonth(Calendar.getInstance())
        loadCalendarData()
        setToday()
        calendarOnClick(binding.tvDate)

        setCardListRcv()
        clickMyFeedImage()
    }

    private fun initView() {
        calendaradapter = CalendarAdapter()
        binding.rcvCalendarDate.apply {
            adapter = calendaradapter
        }
    }

    private fun calendarOnClick(calendarBtn: TextView) {
        calendarBtn.setOnClickListener {
            if (isFirstAnim) {
                firstAnim()
                isFirstAnim = false
            }
            toggleHomeView(binding.layoutDate)
        }
    }

    private fun showCurrentMonth(calendar: Calendar) {
        val currentMonth = (calendar.get(Calendar.MONTH)) + 1
        binding.textviewCurrentMonth.text = "${currentMonth}월"
    }

    private fun toggleHomeView(dateText: ConstraintLayout) {
        if (isCardView) {
            dateText.setBackgroundResource(R.drawable.main_calendar_button)
            binding.tvDate.setTextColor(Color.parseColor("#F6FAFB"))
            binding.imageviewArrowCalendar.visibility = View.VISIBLE
            binding.imageviewArrowCard.visibility = View.INVISIBLE
            calendarViewVisibility()
            isCardView = false
            return
        }
        dateText.setBackgroundResource(R.drawable.main_date_button)
        binding.tvDate.setTextColor(Color.parseColor("#17234D"))
        binding.imageviewArrowCalendar.visibility = View.INVISIBLE
        binding.imageviewArrowCard.visibility = View.VISIBLE
        cardViewVisibility()
        isCardView = true
    }

    fun goCardHomeFragment(){
        binding.layoutDate.setBackgroundResource(R.drawable.main_date_button)
        binding.tvDate.setTextColor(Color.parseColor("#17234D"))
        binding.imageviewArrowCalendar.visibility = View.INVISIBLE
        binding.imageviewArrowCard.visibility = View.VISIBLE
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

    private fun firstAnim() {
        binding.layoutHomeCardView.apply {
            visibility = View.VISIBLE
            animate()
                .alpha(0f)
                .setDuration(mediumAnimationDuration.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        visibility = View.GONE
                    }
                })
        }
        binding.layoutHomeCalendarView.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .setDuration(mediumAnimationDuration.toLong())
                .setListener(null)
        }
    }

    private fun clickMyFeedImage() {
        binding.ivMyPage.setOnClickListener {
            changeIntent(MyFeedMainActivity())
        }
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

        homeCardAdapter.setItemClickListener(
            object : HomeCardAdapter.ItemClickListener {
                override fun onClick(view: View, position: Int) {
                    when (position) {
                        0 -> changeIntent(CardTimeStampActivity())
                        1 -> changeIntent(CardPromiseActivity())
                        2 -> changeIntent(CardWriteDiaryActivity())
                        3 -> changeIntent(CardReadingActivity())
                    }
                }
            })
    }

    private fun changeIntent(activity: AppCompatActivity) {
        val intent = Intent(requireContext(), activity::class.java)
        startActivity(intent)
    }

    private fun setToday() {
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREAN)
        binding.tvDate.text = dateFormat.format(Calendar.getInstance().time)
    }

    private fun loadCalendarData() {
        val call: Call<BaseResponse<CalendarResponse>> =
            MeaningService.getInstance().getCalendar(meaningToken)
        call.enqueueListener(
            onSuccess = {
                val calendar = it.body()!!.data!!.calendar
                binding.textviewDateLabelCount.text = "${it.body()!!.data!!.successDays}번째"

                for (i in calendar.indices) {
                    starData.add(calendar[i].status)
                }
                calendaradapter.dataToAdapter(starData.toList())
            },
            onError = {
            }
        )
    }
}