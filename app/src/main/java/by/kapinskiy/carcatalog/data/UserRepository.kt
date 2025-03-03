package by.kapinskiy.carcatalog.data

import by.kapinskiy.carcatalog.data.db.AuthDataSource
import by.kapinskiy.carcatalog.data.db.UserDataSource
import by.kapinskiy.carcatalog.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class UserRepository(
    private val authDataSource: AuthDataSource,
    private val userDataSource: UserDataSource
) {
    suspend fun login(email: String, password: String) = authDataSource.signIn(email, password)
    suspend fun register(email: String, password: String) = authDataSource.signUp(email, password)
    fun isUserLoggedIn(): Boolean {
        return authDataSource.isUserLoggedIn();
    }

    fun logout() {
        authDataSource.logout()
    }

    suspend fun delete(): Boolean {
        return authDataSource.delete()
    }

    suspend fun getUserProfile(): UserProfile? {
        return userDataSource.getUserProfile()
    }

    suspend fun saveUserProfile(userProfile: UserProfile) {
        userDataSource.saveUserProfile(userProfile)
    }


}
