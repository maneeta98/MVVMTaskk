package com.example.chaipani.viewmodels

import UserRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
//import com.example.chaipani.repositories.UserRepository
import com.google.firebase.firestore.FirebaseFirestore

class ForgetPasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository = UserRepository(FirebaseFirestore.getInstance())

    val resetResult = MutableLiveData<Result<String>>()

    fun resetPassword(email: String) {
        if (email.isEmpty()) {
            resetResult.postValue(Result.failure(Exception("Enter your email")))
            return
        }

        repository.sendPasswordResetEmail(email) { success, message ->
            if (success) {
                resetResult.postValue(Result.success("Password reset email sent to $email"))
            } else {
                resetResult.postValue(Result.failure(Exception(message)))
            }
        }
    }
}
