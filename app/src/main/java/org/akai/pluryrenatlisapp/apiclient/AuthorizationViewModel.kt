package org.akai.pluryrenatlisapp.apiclient

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorizationViewModel: ViewModel() {
    private val _token = MutableLiveData<String>()
    val token: LiveData<String>
        get() = _token

    fun authorize(identification: String, onSuccess: () -> Unit = {}, onError: (Exception) -> Unit = {}) {
        viewModelScope.launch(Dispatchers.IO) {
            TODO("API authorization request")
            onSuccess()
        }
    }
}