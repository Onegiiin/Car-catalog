package by.kapinskiy.carcatalog.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kapinskiy.carcatalog.R
import by.kapinskiy.carcatalog.model.Car
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class CarDetailFragment : Fragment(R.layout.fragment_car_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val car: Car = arguments?.getParcelable("car") ?: return

        val carTitleTextView: TextView = view.findViewById(R.id.carDetailTitleTextView)
        val carPriceTextView: TextView = view.findViewById(R.id.carDetailPriceTextView)
        val carPowerTextView: TextView = view.findViewById(R.id.carDetailPowerTextView)
        val carTorqueTextView: TextView = view.findViewById(R.id.carDetailTorqueTextView)
        val carDrivetrainTextView: TextView = view.findViewById(R.id.carDetailDrivetrainTextView)
        val carAccelerationTextView: TextView = view.findViewById(R.id.carDetailAccelerationTextView)
        val carTransmissionTextView: TextView = view.findViewById(R.id.carDetailTransmissionTextView)
        val carEngineVolumeTextView: TextView = view.findViewById(R.id.carDetailEngineVolumeTextView)
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

        val imageAdapter = CarImageAdapter(car.images)
        viewPager.adapter = imageAdapter

        val backButton: ImageButton = view.findViewById(R.id.backToCatalogButton)
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
