package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Constant.Companion.DEPARTMENT_ITEM_TYPE
import com.example.myapplication.Constant.Companion.OFFER_ITEM_TYPE
import com.example.myapplication.databinding.DepartmentsBinding
import com.example.myapplication.databinding.ViewPagerItemBinding
import com.example.myapplication.models.MainModel
import com.example.myapplication.models.Model

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MainModel>() {
        override fun areItemsTheSame(oldItem: MainModel, newItem: MainModel): Boolean {
            return oldItem.type == newItem.type
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MainModel, newItem: MainModel): Boolean {
            return oldItem == newItem
        }
    }

    val items = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       if(viewType == OFFER_ITEM_TYPE) {
           val itemBinding: ViewPagerItemBinding = ViewPagerItemBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
           return OffersViewHolder(itemBinding)
       } else{
           val itemBinding: DepartmentsBinding = DepartmentsBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
           return DepertmentViewHolder(itemBinding)
       }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OffersViewHolder -> {
                val offers=items.currentList.get(position).models
                holder.bind(offers)
            }
            is DepertmentViewHolder -> {
                val departments=items.currentList.get(position).models
                holder.bindDepertment(departments)
            }
        }
    }

    override fun getItemCount(): Int = items.currentList.size

    override fun getItemViewType(position: Int): Int {
        return when (items.currentList.get(position).type) {
            OFFER_ITEM_TYPE -> return OFFER_ITEM_TYPE
            DEPARTMENT_ITEM_TYPE -> return DEPARTMENT_ITEM_TYPE
            else -> super.getItemViewType(position)
        }
    }

    inner class OffersViewHolder(val viewPagerItemBinding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(viewPagerItemBinding.root) {
        fun bind(offers: List<Model>) {
            val offersAdapter = OfferAdapter()
            offersAdapter.offersDiffer.submitList(offers)
            viewPagerItemBinding.pager.apply {
                adapter = offersAdapter
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
            viewPagerItemBinding.indicator.setViewPager(viewPagerItemBinding.pager)
        }
    }

    inner class DepertmentViewHolder(private val depertmentsBinding: DepartmentsBinding) :
        RecyclerView.ViewHolder(depertmentsBinding.root) {
        fun bindDepertment(departmentList: List<Model>) {
            val departmentAdapter = DepartmentAdapter()
            departmentAdapter.departmentsDiffer.submitList(departmentList)
            depertmentsBinding.departmentsRv.apply {
                adapter = departmentAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }
}