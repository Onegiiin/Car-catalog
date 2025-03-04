package by.kapinskiy.carcatalog.data

import by.kapinskiy.carcatalog.data.db.CarDataSource
import by.kapinskiy.carcatalog.model.Car
import javax.inject.Inject

class CarRepository @Inject constructor(
    private val dataSource: CarDataSource
) {
    suspend fun getCars(): List<Car> = dataSource.getCars()
}
