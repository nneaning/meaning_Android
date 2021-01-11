package meaning.morning.presentation



import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meaning.morning.R
import meaning.morning.databinding.FragmentHomeBinding
import meaning.morning.databinding.HomeCardListItemBinding
import meaning.morning.presentation.adapter.HomeCardAdapter
import meaning.morning.presentation.data.HomeCardData
import meaning.morning.utils.HomeCardItemDecoreation


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCardListRcv()

    }


    private fun setCardListRcv() {
        val homeCardData = mutableListOf<HomeCardData>()

        val homeCardAdapter by lazy {
            HomeCardAdapter{ position: Int, cardItem: HomeCardData ->
                binding.rcvHomeMain.smoothScrollToPosition(position)
            }
        }

        binding.rcvHomeMain.apply {
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
    }
}