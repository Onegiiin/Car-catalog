package by.kapinskiy.carcatalog.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kapinskiy.carcatalog.R
import by.kapinskiy.carcatalog.model.Car
import com.bumptech.glide.Glide

class CarAdapter(
    private val onCarClick: (Car) -> Unit
) : ListAdapter<Car, CarAdapter.CarViewHolder>(CarDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = getItem(position)
        holder.bind(car)
        holder.itemView.setOnClickListener { onCarClick(car) }
    }

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carImageView: ImageView = itemView.findViewById(R.id.carImageView)
        private val carBrandModelTextView: TextView = itemView.findViewById(R.id.carBrandModelTextView)
        private val carPriceTextView: TextView = itemView.findViewById(R.id.carPriceTextView)

        fun bind(car: Car) {
            carBrandModelTextView.text = "${car.brand} ${car.model}"
            carPriceTextView.text = "$${car.price}"
            Glide.with(itemView.context)
                .load(car.imageUrl)
                .placeholder(R.drawable.ic_car_placeholder)
                .into(carImageView)
        }
    }
}

class CarDiffCallback : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Car, newItem: Car) = oldItem == newItem
}
