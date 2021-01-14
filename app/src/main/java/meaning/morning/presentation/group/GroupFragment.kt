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
import meaning.morning.R
import meaning.morning.data.GroupData
import meaning.morning.data.RecommendGroupData
import meaning.morning.databinding.FragmentGroupBinding
import meaning.morning.network.MeaningService
import meaning.morning.network.MeaningService.Companion.meaningToken
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.GroupListResponse
import meaning.morning.network.response.MyGroupResponse
import meaning.morning.presentation.adapter.group.GroupAdapter
import meaning.morning.presentation.adapter.group.RecommendGroupAdapter
import meaning.morning.utils.customEnqueue
import meaning.morning.utils.showError
import retrofit2.Call


class GroupFragment : Fragment() {
    private lateinit var groupAdapter: GroupAdapter
    private lateinit var recommendAdapter: RecommendGroupAdapter
    private lateinit var binding: FragmentGroupBinding

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
    }

    private fun hasMyGroup() {
        val call: Call<BaseResponse<MyGroupResponse>> =
            MeaningService.getInstance().getMyGroup(meaningToken)
        call.customEnqueue(
            onSuccess = {
                if(it.data==null) {
                    binding.layoutMyGroupNull.visibility = View.VISIBLE
                } else
                binding.textviewGroupName.text = it.data?.groupName
                binding.textviewNumber.text = it.data?.countMember.toString() + "/" + it.data?.maximumMemberNumber.toString()
                binding.layoutMyGroupNull.visibility = View.INVISIBLE
                binding.layoutMyGroup.visibility = View.VISIBLE
            },
            onError = {
                showError(requireContext(), it)
            }
        )
    }

    private fun loadNoImageGroup() {
        val call: Call<BaseResponse<GroupListResponse>> =
            MeaningService.getInstance().getGroupList(meaningToken)
        call.customEnqueue(
            onSuccess = {
                val noImageGroup = it.data?.noImageGroupList
                val noImageGroupData = mutableListOf<GroupData>()
                for (i in noImageGroup!!.indices) {
                    noImageGroupData.apply {
                        add(
                            GroupData(
                                noImageGroup[i].groupName,
                                noImageGroup[i].countMember.toString() + "/" + noImageGroup[i].maximumMemberNumber.toString()
                            )
                        )
                    }
                }
                groupAdapter.refreshData(noImageGroupData)
            },
            onError = {
                showError(requireContext(), it)
            }
        )
    }

    private fun loadHasImageGroup() {
        val call: Call<BaseResponse<GroupListResponse>> =
            MeaningService.getInstance().getGroupList(meaningToken)
        call.customEnqueue(
            onSuccess = {
                val ImageGroupList = it.data?.hasImageGroupList
                val hasImageGroupData = mutableListOf<RecommendGroupData>()
                for (i in ImageGroupList!!.indices) {
                    hasImageGroupData.apply {
                        add(
                            RecommendGroupData(
                                ImageGroupList[i].groupName,
                                ImageGroupList[i].countMember.toString(),
                                ImageGroupList[i].imageUrl
                            )
                        )
                    }
                }
                recommendAdapter.refreshData(hasImageGroupData.toMutableList())
            },
            onError = {
                showError(requireContext(), it)
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