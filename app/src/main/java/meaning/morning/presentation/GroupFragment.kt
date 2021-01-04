package meaning.morning.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import meaning.morning.*
import meaning.morning.data.GroupData
import meaning.morning.data.RecommendGroupData
import meaning.morning.databinding.FragmentGroupBinding
import meaning.morning.presentation.adapter.GroupAdapter
import meaning.morning.presentation.adapter.RecommendGroupAdapter


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
        setGroupAdapter(view)
        loadMyGroupData()
        setRecommendGroupAdapter(view)
        loadRecommendGroupData()
    }

    private fun setGroupAdapter(view: View) {
        groupAdapter = GroupAdapter(view.context)
        binding.rcvOtherGroup.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }

    private fun loadMyGroupData() {
        var myGroupData = mutableListOf<GroupData>()

        myGroupData.apply {
            add(
                GroupData(
                    "CPA 공시생 아침인증 그룹",
                    "3/5"
                )
            )
            add(
                GroupData(
                    "서울 대학생 그룹",
                    "2/5"
                )
            )
            add(
                GroupData(
                    "진수 공주 그룹",
                    "1/5"
                )
            )
            add(
                GroupData(
                    "형준 공주 그룹",
                    "4/5"
                )
            )
        }
        groupAdapter.data = myGroupData
        groupAdapter.notifyDataSetChanged()
    }

    private fun setRecommendGroupAdapter(view: View) {
        recommendAdapter = RecommendGroupAdapter(view.context)
        binding.rcvGroupRecommend.apply {
            adapter = recommendAdapter
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun loadRecommendGroupData() {
        var recommendGroupData = mutableListOf<RecommendGroupData>()

        recommendGroupData.apply {
            add(
                RecommendGroupData(
                    "경기 안양 미라클 모임",
                    "25",
                    R.drawable.group_card_1_img
                )
            )
            add(
                RecommendGroupData(
                    "서울 대학생 그룹",
                    "55",
                    R.drawable.group_card_2_img
                )
            )
            add(
                RecommendGroupData(
                    "진수 최고 그룹",
                    "13",
                    R.drawable.group_card_3_img
                )
            )
            add(
                RecommendGroupData(
                    "형준 바보 그룹",
                    "99",
                    R.drawable.group_card_4_img
                )
            )
        }
        recommendAdapter.data = recommendGroupData
        recommendAdapter.notifyDataSetChanged()
    }
}