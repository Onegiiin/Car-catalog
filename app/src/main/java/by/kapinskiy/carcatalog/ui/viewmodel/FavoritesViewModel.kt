package by.kapinskiy.carcatalog.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.kapinskiy.carcatalog.data.FavoritesRepository
import by.kapinskiy.carcatalog.model.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
) : ViewModel() {

    private val _favoriteCars = MutableLiveData<Map<String, Car>>()
    val favoriteCars: LiveData<Map<String, Car>> = _favoriteCars

    private val _favoriteState = MutableLiveData<Boolean>()
    val favoriteState: LiveData<Boolean> get() = _favoriteState


    fun loadFavorites() {
        viewModelScope.launch {
            val cars = repository.getFavoriteCars()
            _favoriteCars.value = cars
        }
    }


    fun setInitialFavoriteState(isFavorite: Boolean) {
        _favoriteState.value = isFavorite
    }

    fun toggleFavorite(car: Car) {
        viewModelScope.launch {
            if (car.isFavorite) {
                repository.removeFavorite(car.id)
                car.isFavorite = false
            } else {
                repository.addFavorite(car)
                car.isFavorite = true
            }

            _favoriteState.value = car.isFavorite
        }
    }

}
