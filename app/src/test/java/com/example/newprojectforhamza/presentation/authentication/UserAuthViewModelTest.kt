package com.example.newprojectforhamza.presentation.authentication

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newprojectforhamza.domain.domainModels.AuthDomainModel
import com.example.newprojectforhamza.domain.useCases.AuthUserCases
import com.example.newprojectforhamza.presentation.ui.authentication.viewmodels.UserAuthViewModel
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UserAuthViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: AuthUserCases

    @Mock
    private lateinit var context: Context

    private lateinit var viewModel: UserAuthViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserAuthViewModel(context, useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `authLogin emits Success state on successful login`() = runTest {
        // Arrange
        val successResponse = listOf(AuthDomainModel(success = true))
        whenever(useCase.userLogin(any(), any()))
            .thenReturn(flowOf(ResourceApiState.Success(successResponse)))

        // Act
        viewModel.authLogin("validUser", "ValidPass1*")

        // Assert
        testDispatcher.scheduler.advanceUntilIdle()
        val state = viewModel.moviesState.value
        Assert.assertTrue(state is ResourceApiState.Success)
        Assert.assertEquals(true, (state as ResourceApiState.Success).data?.first()?.success)
    }

    @Test
    fun `authRegister emits Success state on successful registration`() = runTest {
        // Arrange
        val successResponse = listOf(AuthDomainModel(success = true))
        whenever(useCase.userRegister(any()))
            .thenReturn(flowOf(ResourceApiState.Success(successResponse)))

        // Act
        viewModel.authRegister("testUser", "Pass123!", "test@mail.com", "1234567890")

        // Assert
        testDispatcher.scheduler.advanceUntilIdle()
        val state = viewModel.moviesState.value
        Assert.assertTrue(state is ResourceApiState.Success)
        Assert.assertEquals(true, (state as ResourceApiState.Success).data?.first()?.success)
    }

//    @Test
//    fun `authRegister creates valid JSON object`() = runTest {
//        // Arrange
//        val successResponse = listOf(AuthDomainModel(success = true))
//        whenever(useCase.userRegister(any()))
//            .thenReturn(flowOf(ResourceApiState.Success(successResponse)))
//
//        // Act
//        viewModel.authRegister("testUser", "Pass123!", "test@mail.com", "1234567890")
//
//        // Assert
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Create argument captor and verify JSON content
//        val jsonCaptor = argumentCaptor<JSONObject>()
//        verify(useCase).userRegister(jsonCaptor.capture())
//
//        val capturedJson = jsonCaptor.firstValue
//        assertEquals("testUser", capturedJson.getString("userName"))
//        assertEquals("Pass123!", capturedJson.getString("password"))
//        assertEquals("test@mail.com", capturedJson.getString("email"))
//        assertEquals("1234567890", capturedJson.getString("phone"))
//    }

    @Test
    fun `authLogin emits Error state on failure`() = runTest {
        // Arrange
        val error = Exception("Invalid credentials")
        val errorState: ResourceApiState<List<AuthDomainModel>> =
            ResourceApiState.Error(
                message = "Login failed",
                throwable = error
            )

        whenever(useCase.userLogin(any(), any()))
            .thenReturn(flowOf(errorState))

        // Act
        viewModel.authLogin("invalid", "credentials")

        // Assert
        testDispatcher.scheduler.advanceUntilIdle()
        val state = viewModel.moviesState.value
        Assert.assertTrue(state is ResourceApiState.Error)
        Assert.assertEquals("Login failed", (state as ResourceApiState.Error).message)
        Assert.assertEquals(error, (state as ResourceApiState.Error).throwable)
    }
}