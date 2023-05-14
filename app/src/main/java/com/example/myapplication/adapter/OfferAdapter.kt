package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.OfferItemBinding
import com.example.myapplication.models.Model

class OfferAdapter : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    private val differCallback = object: DiffUtil.ItemCallback<Model>(){
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem == newItem
        }
    }

    val offersDiffer= AsyncListDiffer(this,differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder{
        val layoutInflater=LayoutInflater.from(parent.context)
        val offerItemBinding=OfferItemBinding.inflate(layoutInflater,parent,false)
        return OfferViewHolder(offerItemBinding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(offersDiffer.currentList.get(position))
    }

    override fun getItemCount(): Int = offersDiffer.currentList.size
    inner class OfferViewHolder(val offerItemBinding: OfferItemBinding) : RecyclerView.ViewHolder(offerItemBinding.root){
        fun bind(offer: Model){
            offerItemBinding.offer=offer
        }
    }

}