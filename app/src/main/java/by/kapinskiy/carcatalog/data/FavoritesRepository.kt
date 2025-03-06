package by.kapinskiy.carcatalog.data


import by.kapinskiy.carcatalog.data.db.FavoritesDataSource
import by.kapinskiy.carcatalog.data.db.UserDataSource
import by.kapinskiy.carcatalog.model.Car
import javax.inject.Inject
import javax.inject.Singleton

class FavoritesRepository @Inject constructor(
    private val dataSource: FavoritesDataSource,
    private val userDataSource: UserDataSource
) {

    suspend fun getFavoriteCars(): Map<String, Car> {
        val userId = userDataSource.getCurrentUserId()
        return if (userId != null) {
            val cars = dataSource.getFavorites(userId)

            val updatedCars = cars.map { car ->
                car.copy(isFavorite = true)
            }

            updatedCars.associateBy { it.id }
        } else {
            emptyMap()
        }
    }

    suspend fun addFavorite(car: Car) {
        val userId = userDataSource.getCurrentUserId()
        if (userId != null) {
            dataSource.addFavorite(userId, car)
        }
    }

    suspend fun removeFavorite(carId: String) {
        val userId = userDataSource.getCurrentUserId()
        if (userId != null) {
            dataSource.removeFavorite(userId, carId)
        }
    }
}

