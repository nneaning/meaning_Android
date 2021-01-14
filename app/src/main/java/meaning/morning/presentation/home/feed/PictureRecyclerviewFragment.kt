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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.data.MyFeedPictureData
import meaning.morning.R
import meaning.morning.databinding.FragmentPictureRecyclerviewBinding
import meaning.morning.presentation.adapter.feed.MyFeedPictureAdapter
import meaning.morning.utils.BindFeedPictureEvent


class PictureRecyclerviewFragment : Fragment() {

    private var myFeedPictureAdapter: MyFeedPictureAdapter? = null
    private var bindFeedPictureEvent: BindFeedPictureEvent? = null

    private lateinit var binding: FragmentPictureRecyclerviewBinding

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

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_picture_recyclerview,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bindFeedPictureEvent?.requestToFeedPictureData()
    }

    fun setAdapter(myFeedPictureData: List<MyFeedPictureData>) {
        myFeedPictureAdapter = MyFeedPictureAdapter()
        myFeedPictureAdapter?.submitData(myFeedPictureData)
        binding.rcvPicture.adapter = myFeedPictureAdapter
    }
}