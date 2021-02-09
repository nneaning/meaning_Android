/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created by jinsu4755
 * DESC:
 */

/*
 * Created by <LEE-HYUNGJUN>
 * DESC:
 */
package meaning.morning.presentation.home.feed

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.data.SnsFeedData
import meaning.morning.databinding.FragmentSnsRecyclerviewBinding
import meaning.morning.presentation.adapter.feed.SnsFeedAdapter
import meaning.morning.utils.BindFeedPictureEvent
import meaning.morning.utils.SnsFeedItemDecoration


class SnsRecyclerviewFragment : Fragment() {

    private var bindFeedPictureEvent: BindFeedPictureEvent? = null

    private lateinit var binding: FragmentSnsRecyclerviewBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is BindFeedPictureEvent) {
            throw RuntimeException(context.toString())
        }
        bindFeedPictureEvent = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sns_recyclerview, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFeedPictureEvent?.requestToFeedPictureData()
    }

    fun setAdapter(snsFeedData: List<SnsFeedData>) {
        Log.d("log11",snsFeedData.toString())
        val snsFeedAdapter = SnsFeedAdapter()
        snsFeedAdapter.submitData(snsFeedData)
        binding.rcvPicture.adapter = snsFeedAdapter
        binding.rcvPicture.addItemDecoration(SnsFeedItemDecoration(requireContext()))

    }
}