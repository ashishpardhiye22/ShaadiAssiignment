package com.ashdroid.shaadiassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashdroid.shaadiassignment.R
import com.ashdroid.shaadiassignment.databinding.ListItemProfileCardBinding
import com.ashdroid.shaadiassignment.db.models.*

class MatchProfileCardListAdapter(private val cardHeight:Int, private val listener: Listener) :
    RecyclerView.Adapter<MatchProfileCardListAdapter.RowHolder>() {
    private val list: MutableList<ViewData> = mutableListOf()

    inner class RowHolder(private val binding: ListItemProfileCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ViewData) = with(binding) {
            viewData = data
            binding.apply {
                cavAccept.setOnClickListener {
                    listener.acceptProfile(data.uid)
                }
                cavDecline.setOnClickListener {
                    listener.declineProfile(data.uid)
                }
            }
        }
    }

    interface Listener {
        fun acceptProfile(uid: String)
        fun declineProfile(uid: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        return RowHolder(
            ListItemProfileCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply {
                root.layoutParams.height = cardHeight
            }
        )

    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(list[holder.bindingAdapterPosition])
    }

    override fun getItemCount(): Int = list.size

    fun submitData(newList: List<ViewData>) {
        val diffCallBack = MatchProfileDiffCallback(
            oldList = list,
            newList = newList
        )
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.list.clear()
        this.list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    data class ViewData(
        val uid: String,
        val fullName: String,
        val genderAge: String,
        val cityStateCountry: String,
        val image: String,
        val actionPerformed: Boolean,
        @StringRes val actionPerformedLabel: Int,
        val profile: MatchProfileEntity
    )
}

class MatchProfileDiffCallback(
    private val oldList: List<MatchProfileCardListAdapter.ViewData>,
    private val newList: List<MatchProfileCardListAdapter.ViewData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].uid == newList[newItemPosition].uid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition] == newList[newItemPosition])
    }
}