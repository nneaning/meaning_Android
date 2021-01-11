package meaning.morning.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.FragmentSnsRecyclerviewBinding
import meaning.morning.presentation.adapter.MyFeedPictureAdapter
import meaning.morning.presentation.adapter.SnsFeedAdapter
import meaning.morning.presentation.data.SnsFeedData
import meaning.morning.utils.BindFeedPictureEvent
import meaning.morning.utils.SnsFeedItemDecoration


class SnsRecyclerviewFragment : Fragment() {

    private var snsFeedAdapter : SnsFeedAdapter? = null
    private var bindFeedPictureEvent : BindFeedPictureEvent? = null

    private lateinit var binding : FragmentSnsRecyclerviewBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is BindFeedPictureEvent){
            throw RuntimeException(context.toString())
        }
        bindFeedPictureEvent = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sns_recyclerview, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFeedPictureEvent?.requestToFeedPictureData()
    }

    fun setAdapter(snsFeedData: List<SnsFeedData>){
        snsFeedAdapter = SnsFeedAdapter()
        snsFeedAdapter?.submitData(snsFeedData)
        binding.rcvPicture.apply {
            adapter = snsFeedAdapter
            addItemDecoration(SnsFeedItemDecoration(requireContext()))
        }
    }
}