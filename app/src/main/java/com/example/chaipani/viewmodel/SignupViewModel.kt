package com.example.chaipani.viewmodels

import User
import UserRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class SignupViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository = UserRepository(FirebaseFirestore.getInstance())

    val signupResult = MutableLiveData<Result<String>>()

    fun signupUser(firstName: String, lastName: String, email: String, phoneNumber: String, password: String, confirmPassword: String) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            signupResult.postValue(Result.failure(Exception("Please fill in all fields")))
            return
        }

        if (password != confirmPassword) {
            signupResult.postValue(Result.failure(Exception("Passwords do not match")))
            return
        }

        val user = User(firstName, lastName, email, phoneNumber)
        repository.createUser(user, password) { success, message ->
            if (success) {
                signupResult.postValue(Result.success("Account created"))
            } else {
                signupResult.postValue(Result.failure(Exception(message)))
            }
        }
    }
}
