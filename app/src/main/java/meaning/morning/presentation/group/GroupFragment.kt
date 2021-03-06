/*
 * Created By: hyooosong
 * on 2021.01.05
 */

package meaning.morning.presentation.group

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import meaning.morning.MeaningStorage
import meaning.morning.R
import meaning.morning.data.GroupData
import meaning.morning.data.RecommendGroupData
import meaning.morning.databinding.FragmentGroupBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupListResponse
import meaning.morning.network.response.MyGroupResponse
import meaning.morning.presentation.adapter.group.GroupAdapter
import meaning.morning.presentation.adapter.group.RecommendGroupAdapter
import meaning.morning.presentation.group.feed.GroupFeedActivity
import meaning.morning.utils.enqueueListener
import retrofit2.Call
import kotlin.properties.Delegates


class GroupFragment : Fragment() {
    private lateinit var groupAdapter: GroupAdapter
    private lateinit var recommendAdapter: RecommendGroupAdapter
    private lateinit var binding: FragmentGroupBinding
    var myGroupId by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGroupAdapter()
        loadNoImageGroup()
        setRecommendGroupAdapter()
        loadHasImageGroup()
        hasMyGroup()


        binding.imageviewAddGroup.setOnClickListener {
            val intent = Intent(activity, AddGroupActivity::class.java)
            startActivity(intent)
        }

        binding.imageviewNext.setOnClickListener {
            val intent = Intent(requireContext(),GroupFeedActivity::class.java)
            startActivity(intent)
        }
    }
    private fun hasMyGroup() {
        val call: Call<BaseResponse<MyGroupResponse>> =
            MeaningService.getInstance().getMyGroup(MeaningStorage.getInstance(requireContext()).accessToken)
        call.enqueueListener(
            onSuccess = {
                if(it.body()?.data==null) {
                    binding.layoutMyGroupNull.visibility = View.VISIBLE
                    return@enqueueListener
                }
                myGroupId = it.body()?.data!!.groupId
                saveMyGroupId()
                binding.textviewGroupName.text = it.body()?.data!!.groupName
                binding.textviewNumber.text = it.body()?.data!!.countMember.toString() + "/" + it.body()?.data!!.maximumMemberNumber.toString()
                binding.layoutMyGroupNull.visibility = View.INVISIBLE
                binding.layoutMyGroup.visibility = View.VISIBLE
            },
            onError = {
            }
        )
    }

    private fun saveMyGroupId() {
        MeaningStorage.getInstance(requireContext()).saveGroupId(myGroupId)
    }

    private fun loadNoImageGroup() {
        val call: Call<BaseResponse<GroupListResponse>> =
            MeaningService.getInstance().getGroupList(MeaningStorage.getInstance(requireContext()).accessToken)
        call.enqueueListener(
            onSuccess = {
                val noImageGroup = it.body()!!.data!!.noImageGroupList
                val noImageGroupData = mutableListOf<GroupData>()
                for (i in noImageGroup.indices) {
                    noImageGroupData.apply {
                        add(
                            GroupData(
                                noImageGroup[i].groupId,
                                noImageGroup[i].groupName,
                                noImageGroup[i].countMember.toString() + "/" + noImageGroup[i].maximumMemberNumber.toString()
                            )
                        )
                    }
                }
                groupAdapter.refreshData(noImageGroupData)
            },
            onError = {
            }
        )
    }

    private fun loadHasImageGroup() {
        val call: Call<BaseResponse<GroupListResponse>> =
            MeaningService.getInstance().getGroupList(MeaningStorage.getInstance(requireContext()).accessToken)
        call.enqueueListener(
            onSuccess = {
                val imageGroupList = it.body()!!.data!!.hasImageGroupList
                val hasImageGroupData = mutableListOf<RecommendGroupData>()
                for (i in imageGroupList.indices) {
                    hasImageGroupData.apply {
                        add(
                            RecommendGroupData(
                                imageGroupList[i].groupId,
                                imageGroupList[i].groupName,
                                imageGroupList[i].countMember.toString(),
                                imageGroupList[i].imageUrl
                            )
                        )
                    }
                }
                recommendAdapter.refreshData(hasImageGroupData.toMutableList())
            },
            onError = {
            }
        )
    }

    private fun setGroupAdapter() {
        groupAdapter = GroupAdapter(requireContext())
        binding.rcvOtherGroup.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setRecommendGroupAdapter() {
        recommendAdapter = RecommendGroupAdapter(requireContext())
        binding.rcvGroupRecommend.apply {
            adapter = recommendAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}