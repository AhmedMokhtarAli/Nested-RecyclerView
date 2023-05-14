package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.DepartmentItemBinding
import com.example.myapplication.models.Model

class DepartmentAdapter (): RecyclerView.Adapter<DepartmentAdapter.DepertmentViewHolder>() {

    private val differCalllBack=object : DiffUtil.ItemCallback<Model>(){
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem == newItem
        }

    }
    val departmentsDiffer= AsyncListDiffer(this,differCalllBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepertmentViewHolder {
        val depertmentItemBinding= DepartmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent
            ,false)
        return DepertmentViewHolder(depertmentItemBinding)
    }

    override fun getItemCount(): Int = departmentsDiffer.currentList.size

    override fun onBindViewHolder(holder: DepertmentViewHolder, position: Int) {
        holder.bindDepertment(departmentsDiffer.currentList.get(position))
    }
    class DepertmentViewHolder(private val depertmentItemBinding: DepartmentItemBinding) :
        RecyclerView.ViewHolder(depertmentItemBinding.root) {
        fun bindDepertment(department: Model){
            depertmentItemBinding.depertment=department
        }
    }
}