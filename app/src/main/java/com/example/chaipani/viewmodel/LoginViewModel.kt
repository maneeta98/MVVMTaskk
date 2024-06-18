package com.example.chaipani.viewmodels

import UserRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository = UserRepository(FirebaseFirestore.getInstance())

    val loginResult = MutableLiveData<Result<String>>()

    fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            loginResult.postValue(Result.failure(Exception("Enter the details")))
            return
        }

        repository.loginUser(email, password) { success, message ->
            if (success) {
                loginResult.postValue(Result.success("Login Successful"))
            } else {
                loginResult.postValue(Result.failure(Exception(message)))
            }
        }
    }
}
