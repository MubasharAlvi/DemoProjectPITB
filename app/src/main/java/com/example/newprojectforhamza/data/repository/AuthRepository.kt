package com.example.newprojectforhamza.data.repository

import com.example.newprojectforhamza.data.remote.models.AuthModelDTO
import com.example.newprojectforhamza.domain.domainModels.AuthDomainModel
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject

interface AuthRepository {
    suspend fun userLogin(userName : String,password : String) : Flow<ResourceApiState<List<AuthDomainModel>>>
    suspend fun userRegister(userReg : JSONObject) : Flow<ResourceApiState<List<AuthDomainModel>>>

}