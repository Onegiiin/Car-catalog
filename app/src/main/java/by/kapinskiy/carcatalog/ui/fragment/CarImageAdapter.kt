package by.kapinskiy.carcatalog.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.kapinskiy.carcatalog.R
import com.bumptech.glide.Glide

class CarImageAdapter(private val images: List<String>) : RecyclerView.Adapter<CarImageAdapter.CarImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car_image, parent, false)

        return CarImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarImageViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(images[position])
            .placeholder(R.drawable.ic_car_placeholder)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = images.size

    inner class CarImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }
}
