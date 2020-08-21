package com.androdevlinux.percy.stackingsats.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.androdevlinux.percy.stackingsats.R
import com.androdevlinux.percy.stackingsats.pojo.contract.Contract

class BuyOrderHistoryAdapter(
    private var contractResponseBeanArrayList: ArrayList<Contract>,
    private val requireContext: Context
) :
    RecyclerView.Adapter<BuyOrderHistoryAdapter.ViewHolder>() {

    override fun getItemCount() = contractResponseBeanArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_buy_order_history, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val status: AppCompatTextView = itemView.findViewById(R.id.status)
        internal val price: AppCompatTextView = itemView.findViewById(R.id.price)
        internal val volume: AppCompatTextView = itemView.findViewById(R.id.volume)
        internal val value: AppCompatTextView = itemView.findViewById(R.id.value)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contract = contractResponseBeanArrayList[position]
        holder.status.text = contract.status!!
        holder.price.text = contract.price!!
        holder.volume.text = contract.volume!!
        holder.value.text = String.format(contract.value!! + " \u20B9", "")
    }
}