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
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.data.GroupFeedListData
import meaning.morning.data.MyFeedMainListData
import meaning.morning.data.MyFeedPictureData
import meaning.morning.databinding.FragmentPictureRecyclerviewBinding
import meaning.morning.presentation.adapter.feed.MyFeedPictureAdapter
import meaning.morning.presentation.group.feed.GroupFeedSnsActivity
import meaning.morning.utils.BindFeedPictureEvent


class PictureRecyclerviewFragment : Fragment() {

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

    fun setAdapter(myFeedPictureData: List<MyFeedPictureData>,myFeedMainList: List<MyFeedMainListData>,successDay : String) {
        val myFeedPictureAdapter = MyFeedPictureAdapter()
        myFeedPictureAdapter.submitData(myFeedPictureData)
        binding.rcvPicture.adapter = myFeedPictureAdapter

        myFeedPictureAdapter.setItemClickListener(
            object : MyFeedPictureAdapter.ItemClickListener {
                override fun onClick(view: View, position: Int) {
                    val intent = Intent(requireContext(), MyFeedSnsActivity::class.java)
                    intent.putParcelableArrayListExtra("myFeedList", myFeedMainList as ArrayList<out Parcelable>)
                    intent.putExtra("successDay",successDay)
                    startActivity(intent)
                }
            })
    }

    fun setGroupAdapter(groupFeedPictureData: List<MyFeedPictureData>, groupFeedMainList : List<GroupFeedListData>){
        val myFeedPictureAdapter = MyFeedPictureAdapter()
        myFeedPictureAdapter.submitData(groupFeedPictureData)
        binding.rcvPicture.adapter = myFeedPictureAdapter

        myFeedPictureAdapter.setItemClickListener(
            object : MyFeedPictureAdapter.ItemClickListener {
                override fun onClick(view: View, position: Int) {
                    val intent = Intent(requireContext(),GroupFeedSnsActivity::class.java)
                    intent.putParcelableArrayListExtra("groupFeedList", groupFeedMainList as ArrayList<out Parcelable>)
                    startActivity(intent)
                }
            })
    }
}