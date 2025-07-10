package com.example.newprojectforhamza

import androidx.test.core.app.ApplicationProvider
import com.example.newprojectforhamza.data.repository.AuthRepositoryImp
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepositoryImpTest {

    private lateinit var repository: AuthRepositoryImp
    private val context = ApplicationProvider.getApplicationContext<android.content.Context>()

    @Before
    fun setup() {
        repository = AuthRepositoryImp(context)
    }
//
//    @Test
//    fun `userLogin returns success for valid credentials`() = runTest {
//        // Act
//        val result = repository.userLogin("testuser", "password").first()
//
//        // Assert
//        assertTrue(result is ResourceApiState.Success)
//        assertEquals(true, (result as ResourceApiState.Success).data?.first()?.success)
//    }
//
//    @Test
//    fun `userLogin returns error for invalid credentials`() = runTest {
//        // Act
//        val result = repository.userLogin("wrong", "credentials").first()
//
//        // Assert
//        assertTrue(result is ResourceApiState.Error)
//        assertEquals(context.getString(R.string.invalid_credentials), (result as ResourceApiState.Error).message)
//    }

//    @Test
//    fun `userRegister returns success for new user`() = runTest {
//        // Arrange
//        val json = JSONObject().apply {
//            put("userName", "newuser")
//            put("password", "NewPass1*")
//            put("email", "new@example.com")
//            put("phone", "1234567890")
//        }
//
//        // Act
//        val result = repository.userRegister(json).first()
//
//        // Assert
//        assertTrue(result is ResourceApiState.Success)
//        assertEquals(true, (result as ResourceApiState.Success).data?.first()?.success)
//    }

//    @Test
//    fun `userRegister returns error for existing user`() = runTest {
//        // Arrange
//        val json = JSONObject().apply {
//            put("userName", "testuser") // Existing user
//            put("password", "Password1*")
//            put("email", "test@gmail.com")
//            put("phone", "1234567890")
//        }
//
//        // Act
//        val result = repository.userRegister(json).first()
//
//        // Assert
//        assertTrue(result is ResourceApiState.Error)
//        assertEquals(context.getString(R.string.username_already_exists), (result as ResourceApiState.Error).message)
//    }
}