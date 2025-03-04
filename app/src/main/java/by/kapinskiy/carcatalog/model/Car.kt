package by.kapinskiy.carcatalog.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val id: String = "",
    val brand: String = "",
    val model: String = "",
    val price: Int = 0,
    val imageUrl: String = "",
    val images: List<String> = emptyList(),
    val power: Int = 0,
    val torque: Int = 0,
    val drivetrain: String = "",
    val acceleration: Double = 0.0,
    val transmission: String = "",
    val engine: String = "",
    val year: Int = 0
) : Parcelable
