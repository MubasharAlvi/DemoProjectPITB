package com.example.newprojectforhamza.presentation.ui.authentication.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.domain.domainModels.AuthDomainModel
import com.example.newprojectforhamza.domain.useCases.AuthUserCases
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class UserAuthViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCase: AuthUserCases
) : ViewModel() {

    private val _authState = MutableStateFlow<ResourceApiState<List<AuthDomainModel>>>(ResourceApiState.Loading())
    val moviesState: StateFlow<ResourceApiState<List<AuthDomainModel>>> = _authState.asStateFlow()

/**
    init { authLogin() }
*/

    fun authLogin(userName : String,password : String) {
        viewModelScope.launch {
            useCase.userLogin(userName = userName, password = password)
                .catch { e ->
                    _authState.value = ResourceApiState.Error(
                        message   = e.localizedMessage ?: context.getString(R.string.unknown_error),
                        throwable = e
                    )
                }
                .collect { state ->
                    _authState.value = state
                }
        }
    }

//    fun authRegister(userName : String,password : String,email: String,phone: String) {
//        viewModelScope.launch {
//            useCase.userRegister(userName = userName, password = password, email = email, phone = phone)
//                .catch { e ->
//                    _authState.value = ResourceApiState.Error(
//                        message   = e.localizedMessage ?: context.getString(R.string.unknown_error),
//                        throwable = e
//                    )
//                }
//                .collect { state ->
//                    _authState.value = state
//                }
//        }
//    }
fun authRegister(userName: String, password: String, email: String, phone: String) {
    viewModelScope.launch {
        try {
            val userRegisterJson = JSONObject().apply {
                put("userName", userName)
                put("password", password)
                put("email", email)
                put("phone", phone)
            }

            useCase.userRegister(userRegisterJson)
                .catch { e ->
                    _authState.value = ResourceApiState.Error(
                        message   = e.localizedMessage ?: context.getString(R.string.unknown_error),
                        throwable = e
                    )
                }
                .collect { state ->
                    _authState.value = state
                }
        } catch (e: Exception) {
            _authState.value = ResourceApiState.Error(
                message   = e.localizedMessage ?: context.getString(R.string.unknown_error),
                throwable = e
            )
        }
    }
}


}