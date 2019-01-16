package com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gmail.dudarenka.vitali.autocattesttask.R
import com.gmail.dudarenka.vitali.autocattesttask.domain.entity.Car


class CarListAdapter : RecyclerView.Adapter<CarListAdapter.Holder>() {
    private var carList: ArrayList<Car>? = ArrayList()
    var listData: MutableList<Car>? = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
            carList!!.addAll(this.listData!!)
        }
    internal var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.car_item, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = listData!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val car = listData!![position]
        holder.makeTextView.text = car.make
        holder.modelTextView.text = car.model
        holder.capacityTextView.text = car.capacity
        if (car.gearBox)
            holder.gearBoxTextView.text = "АКПП"
        else
            holder.gearBoxTextView.text = "МКПП"
        holder.yearTextView.text = car.year
        holder.priceTextView.text = car.price
        Glide.with(holder.itemView.context)
                .load(car.imgUrl)
                .into(holder.imageView)
    }

    inner class Holder : RecyclerView.ViewHolder {
        var makeTextView: TextView
        var modelTextView: TextView
        var capacityTextView: TextView
        var gearBoxTextView: TextView
        var yearTextView: TextView
        var priceTextView: TextView
        var imageView: ImageView

        constructor(view: View) : super(view) {
            makeTextView = view.findViewById(R.id.makeTextView)
            modelTextView = view.findViewById(R.id.modelTextView)
            imageView = view.findViewById(R.id.itemImageView)
            capacityTextView = view.findViewById(R.id.capacityTextView)
            gearBoxTextView = view.findViewById(R.id.gearBoxTextView)
            yearTextView = view.findViewById(R.id.yearTextView)
            priceTextView = view.findViewById(R.id.priceTextView)
            itemView.setOnClickListener {
                val user = listData!![layoutPosition]
                onItemClickListener!!.onItemClick(user)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(car: Car)
    }

}