package com.example.newprojectforhamza.data.repository

import android.content.Context
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.data.remote.models.AuthEntryDTO
import com.example.newprojectforhamza.data.remote.models.UserRegisterModelDTO
import com.example.newprojectforhamza.data.utils.AuthMockFileHelper
import com.example.newprojectforhamza.domain.domainModels.AuthDomainModel
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    @ApplicationContext private val context: Context
) : AuthRepository {

    override suspend fun userLogin(
        userName: String,
        password: String
    ): Flow<ResourceApiState<List<AuthDomainModel>>> = flow {
        AuthMockFileHelper.ensureWritable(context)
        val fileDTO = AuthMockFileHelper.read(context)

        val entry = fileDTO.entries.find {
            it.data.user == userName && it.data.password == password
        }


        if (entry == null) {
            emit(ResourceApiState.Error(context.getString(R.string.invalid_credentials)))
        } else {
            emit(
                ResourceApiState.Success(
                    listOf(
                        AuthDomainModel(
                            success = true,
                            message = entry.message,
                            token = entry.tokenAuth
                        )
                    )
                )
            )
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun userRegister(
       userReg : JSONObject
    ): Flow<ResourceApiState<List<AuthDomainModel>>> = flow {

        AuthMockFileHelper.ensureWritable(context)

        val fileDTO = AuthMockFileHelper.read(context)

        if (fileDTO.entries.any { it.data.user == userReg.getString("userName") }) {
            emit(ResourceApiState.Error(context.getString(R.string.username_already_exists)))
            return@flow
        }

        val newToken = "token_${userReg.getString("userName")}_${System.currentTimeMillis()}"
        fileDTO.entries += AuthEntryDTO(
            message = context.getString(R.string.successfully),
            tokenAuth = newToken,
            data = UserRegisterModelDTO(user = userReg.getString("userName"), password = userReg.getString("password"), email = userReg.getString("email"), phone=userReg.getString("phone"))
        )

        AuthMockFileHelper.write(context, fileDTO)

        emit(
            ResourceApiState.Success(
                listOf(
                    AuthDomainModel(
                        success = true,
                        message = context.getString(R.string.registration_successful),
                        token = newToken
                    )
                )
            )
        )
    }.flowOn(Dispatchers.IO)


}
