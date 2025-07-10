package com.example.newprojectforhamza.domain.useCases

import com.example.newprojectforhamza.data.repository.AuthRepository
import com.example.newprojectforhamza.domain.domainModels.AuthDomainModel
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject
import javax.inject.Inject

class AuthUserCases @Inject constructor(private val authRepository: AuthRepository){
    suspend fun userLogin(userName: String,password: String) : Flow<ResourceApiState<List<AuthDomainModel>>> = authRepository.userLogin(userName, password)

    suspend fun userRegister(userReg: JSONObject): Flow<ResourceApiState<List<AuthDomainModel>>> =
        authRepository.userRegister(userReg)
}