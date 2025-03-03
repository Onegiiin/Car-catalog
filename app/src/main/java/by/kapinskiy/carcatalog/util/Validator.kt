package by.kapinskiy.carcatalog.util

import android.util.Patterns
import by.kapinskiy.carcatalog.model.UserProfile

class Validator {
    companion object {
        fun validateCredentials(email: String, password: String): String? {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return "Please enter a valid email"
            }
            if (password.length < 6) {
                return "Password must be at least 6 characters long"
            }
            return null
        }

        fun validateProfile(userProfile: UserProfile): String? {
            if (userProfile.fullName.isNullOrEmpty()) return "Full name is required"
            if (userProfile.birthDate.isNullOrEmpty()) return "Birth date is required"
            if (userProfile.description.isNullOrEmpty()) return "Description is required"
            if (userProfile.phoneNumber.isNullOrEmpty()) return "Phone number is required"
            if (userProfile.address.isNullOrEmpty()) return "Address is required"
            if (userProfile.profilePictureUrl.isNullOrEmpty()) return "Profile picture URL is required"
            if (userProfile.gender.isNullOrEmpty()) return "Gender is required"
            if (userProfile.occupation.isNullOrEmpty()) return "Occupation is required"
            if (userProfile.interests.isNullOrEmpty()) return "Interests are required"
            return null
        }
    }



}
