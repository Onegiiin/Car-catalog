package by.kapinskiy.carcatalog.data

import by.kapinskiy.carcatalog.data.db.CarDataSource
import by.kapinskiy.carcatalog.model.Car
import javax.inject.Inject

class CarRepository @Inject constructor(
    private val dataSource: CarDataSource,
    private val favoritesRepository: FavoritesRepository
) {

    suspend fun getCars(): List<Car> {
        val cars = dataSource.getCars()

        val favoriteCars = favoritesRepository.getFavoriteCars()

        return cars.map { car ->
            car.copy(isFavorite = favoriteCars.contains(car.id))
        }
    }
}
