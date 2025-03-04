package by.kapinskiy.carcatalog.data.db

import android.util.Log
import by.kapinskiy.carcatalog.model.Car
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CarDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getCars(): List<Car> {
        return try {
/*            val car = Car(
                brand = "Jaguar",
                model = "F-Type",
                price = 67000,
                imageUrl = "https://avatars.mds.yandex.net/get-autoru-vos/2170011/559a24af01cb89c46e4af52344167c56/1200x900n",
                images = listOf("https://avatars.mds.yandex.net/get-autoru-vos/1986206/c144cf3f45f215baab6548eea8c0f6c0/1200x900n",
                    "https://avatars.mds.yandex.net/get-autoru-vos/3932535/fadb888ed56f277985c3c78342e3fb0c/1200x900n",
                    "https://avatars.mds.yandex.net/get-autoru-vos/1665854/c8fc277a149c7593c873ab8bf9503fde/1200x900n",
                    "https://avatars.mds.yandex.net/get-autoru-vos/2180453/4933b859ee6a7e6e26d97d02dae002ea/1200x900n",
                    "https://avatars.mds.yandex.net/get-autoru-vos/2180453/4933b859ee6a7e6e26d97d02dae002ea/1200x900n"),
                power = 300,
                torque = 400,
                drivetrain = "RWD",
                acceleration = 5.7,
                transmission = "8-speed automatic",
                engine = "i4 2.0",
                year = 2019
            )

            firestore.collection("cars")
                .add(car)
                .addOnSuccessListener { documentReference ->
                    println("Car added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Error adding car: $e")
                }*/

            val snapshot = firestore.collection("cars").get().await()
            snapshot.documents.mapNotNull { doc ->
                doc.toObject(Car::class.java)?.copy(id = doc.id)
            }
        } catch (e: Exception) {
            Log.e("Firestore", "Error getting cars", e)
            emptyList()
        }
    }

}
