package by.kapinskiy.carcatalog.model

data class UserProfile(
    val fullName: String? = null,
    val birthDate: String? = null,
    val description: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val email: String? = null,
    val profilePictureUrl: String? = null,
    val gender: String? = null,
    val occupation: String? = null,
    val interests: String? = null
)
