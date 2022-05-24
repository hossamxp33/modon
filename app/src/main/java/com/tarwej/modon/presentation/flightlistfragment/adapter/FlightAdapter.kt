package com.tarwej.modon.presentation.flightlistfragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tarwej.modon.ClickHandler
import com.tarwej.modon.MainActivity
import com.tarwej.modon.R
import com.tarwej.modon.databinding.FlightListAdapterBinding

import com.tarwej.modon.databinding.FlightListFragmentBinding
import com.tarwej.modon.entities.trips.Data
import com.tarwej.modon.entities.trips.TripModel
import com.tarwej.modon.presentation.flightlistfragment.mvi.TripViewModel

class FlightAdapter(var context: Context?) : ListAdapter<Data, ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding: FlightListAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(p0.context),
            R.layout.flight_list_adapter, p0, false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, currentList[position])


    }

}

private class DiffCallback : DiffUtil.ItemCallback<Data>() {

    override fun areItemsTheSame(
        oldItem: Data, newItem: Data
    ) =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: Data, newItem: Data
    ) =
        oldItem == newItem
}


class ViewHolder(
    val binding: FlightListAdapterBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(context: Context?, data: Data) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
    }


}
