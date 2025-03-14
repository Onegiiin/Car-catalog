package by.kapinskiy.carcatalog.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kapinskiy.carcatalog.R
import by.kapinskiy.carcatalog.model.Car
import by.kapinskiy.carcatalog.ui.viewmodel.FavoritesViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CarDetailFragment : Fragment(R.layout.fragment_car_detail) {

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var likeButton: ImageButton
    private lateinit var car: Car

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        car = arguments?.getParcelable("car") ?: return
        likeButton = view.findViewById(R.id.likeButton)

        val carTitleTextView: TextView = view.findViewById(R.id.carDetailTitleTextView)
        val carPriceTextView: TextView = view.findViewById(R.id.carDetailPriceTextView)
        val carPowerTextView: TextView = view.findViewById(R.id.carDetailPowerTextView)
        val carTorqueTextView: TextView = view.findViewById(R.id.carDetailTorqueTextView)
        val carDrivetrainTextView: TextView = view.findViewById(R.id.carDetailDrivetrainTextView)
        val carAccelerationTextView: TextView =
            view.findViewById(R.id.carDetailAccelerationTextView)
        val carTransmissionTextView: TextView =
            view.findViewById(R.id.carDetailTransmissionTextView)
        val carEngineVolumeTextView: TextView =
            view.findViewById(R.id.carDetailEngineVolumeTextView)
        val carYearTextView: TextView = view.findViewById(R.id.carDetailYearTextView)
        val viewPager: ViewPager2 = view.findViewById(R.id.carImageViewPager)

        carTitleTextView.text = "${car.brand} ${car.model}"
        carPriceTextView.text = "$${car.price}"
        carPowerTextView.text = "Power: ${car.power} HP"
        carTorqueTextView.text = "Torque: ${car.torque} Nm"
        carDrivetrainTextView.text = "Drivetrain: ${car.drivetrain}"
        carAccelerationTextView.text = "0-100 km/h: ${car.acceleration} sec"
        carTransmissionTextView.text = "Transmission: ${car.transmission}"
        carEngineVolumeTextView.text = "Engine: ${car.engine}"
        carYearTextView.text = "Year: ${car.year}"
        updateLikeIcon(car.isFavorite)

        val imageAdapter = CarImageAdapter(car.images)
        viewPager.adapter = imageAdapter

        val backButton: ImageButton = view.findViewById(R.id.backToCatalogButton)
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.setInitialFavoriteState(car.isFavorite)

        viewModel.favoriteState.observe(viewLifecycleOwner) { isFavorite ->
            updateLikeIcon(isFavorite)
        }

        likeButton.setOnClickListener {
            viewModel.toggleFavorite(car)
        }
    }

    private fun updateLikeIcon(isFavorite: Boolean) {
        val iconRes =
            if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_empty
        val colorRes = if (isFavorite) R.color.colorActiveIcon else R.color.colorInactiveIcon

        likeButton.setImageResource(iconRes)
        likeButton.setColorFilter(ContextCompat.getColor(requireContext(), colorRes))

    }

}
