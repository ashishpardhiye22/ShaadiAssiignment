package com.ashdroid.shaadiassignment.fragments.profile.list

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.ashdroid.shaadiassignment.adapters.MatchProfileCardListAdapter
import com.ashdroid.shaadiassignment.databinding.FragmentProfileCardListBinding
import com.ashdroid.shaadiassignment.helpers.screeHeight
import com.ashdroid.shaadiassignment.helpers.toViewData
import kotlinx.coroutines.flow.collectLatest

class ProfileCardListFragment : Fragment(), MatchProfileCardListAdapter.Listener {

    private val viewModel by viewModels<ProfileCardListViewModel>()
    private lateinit var binding: FragmentProfileCardListBinding
    private lateinit var adapter: MatchProfileCardListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentProfileCardListBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initData()
    }

    private fun initUI() {
        binding.apply {
            rvList.layoutManager = LinearLayoutManager(context)
            rvList.adapter =
                MatchProfileCardListAdapter(
                    cardHeight = (screeHeight() * 0.7f).toInt(),
                    listener = this@ProfileCardListFragment
                ).also { adapter = it }
            PagerSnapHelper().attachToRecyclerView(rvList)
        }
    }

    private fun initData() {
        lifecycleScope.launchWhenStarted {
            viewModel.getAllProfiles().collectLatest {
                adapter.submitData(it.map { it.toViewData() })
                binding.progress.isVisible = false
            }
        }
        viewModel.fetchProfiles()
    }

    override fun acceptProfile(uid: String) {
        viewModel.acceptProfile(uid)
    }

    override fun declineProfile(uid: String) {
        viewModel.declineProfile(uid)
    }

}