package com.example.newprojectforhamza.presentation.ui.authentication

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.databinding.FragmentRegisterBinding
import com.example.newprojectforhamza.presentation.ui.authentication.viewmodels.UserAuthViewModel
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class UserRegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val vm: UserAuthViewModel by viewModels()
    private  var token : Boolean?=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.moviesState.collect { state ->
                    when (state) {
                        is ResourceApiState.Loading -> binding.progressbarId.visibility = View.GONE
                        is ResourceApiState.Success -> {
                            binding.progressbarId.visibility = View.GONE
                            val data = state.data?.map { token = it.success } ?: return@collect
                            if (validateAndRegister(
                                    "validUser",
                                    "test@example.com",
                                    "1234567890",
                                    "ValidPass1*",
                                    "DifferentPass1*"
                                ) && token==true) {
                        //        findNavController().navigateUp()
                            }
                        }
                        is ResourceApiState.Error -> {
                            binding.progressbarId.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                state.message ?: getString(R.string.unknown_errors),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            val username = binding.etUserName.text?.toString()?.trim().orEmpty()
            val email = binding.etEmail.text?.toString()?.trim().orEmpty()
            val phone = binding.etPhone.text?.toString()?.trim().orEmpty()
            val password = binding.etPassword.text?.toString().orEmpty()
            if (validateAndRegister(
                    "validUser",
                    "test@example.com",
                    "1234567890",
                    "ValidPass1*",
                    "DifferentPass1*"
                )
            ) {
                vm.authRegister(userName = username, password = password, email = email, phone = phone)
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
     fun validateAndRegister(
         string: String,
         string1: String,
         string2: String,
         string3: String,
         string4: String
     ): Boolean {
        val username = binding.etUserName.text?.toString()?.trim().orEmpty()
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val phone = binding.etPhone.text?.toString()?.trim().orEmpty()
        val password = binding.etPassword.text?.toString().orEmpty()
        val confirmPassword = binding.etConfirmPassword.text?.toString().orEmpty()

        var isValid = true

        val USERNAME_RULE = Regex("^[A-Za-z]{3,16}$")
        val EMAIL_RULE = Patterns.EMAIL_ADDRESS
        val PHONE_RULE = Regex("^\\d{10,15}$")
        val PASSWORD_RULE = Regex("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*[*#!\$]$")

        if (!USERNAME_RULE.matches(username)) {
            binding.userNameInputLayout.error = "Username must be 3-16 letters"
            isValid = false
        } else {
            binding.userNameInputLayout.error = null
        }

        if (!EMAIL_RULE.matcher(email).matches()) {
            binding.emailInputLayout.error = "Enter a valid email"
            isValid = false
        } else {
            binding.emailInputLayout.error = null
        }

        if (!PHONE_RULE.matches(phone)) {
            binding.phoneInputLayout.error = "Enter a valid phone number"
            isValid = false
        } else {
            binding.phoneInputLayout.error = null
        }

        if (!PASSWORD_RULE.matches(password)) {
            binding.passwordInputLayout.error =
                "Password must be 8+ chars with A-Z, a-z, number, and ends with *#!$"
            isValid = false
        } else {
            binding.passwordInputLayout.error = null
        }

        if (password != confirmPassword) {
            binding.confirmPasswordInputLayout.error = "Passwords do not match"
            isValid = false
        } else {
            binding.confirmPasswordInputLayout.error = null
        }

        if (!isValid) {
            Toast.makeText(requireContext(), "Please fix the errors above", Toast.LENGTH_SHORT).show()
        }

        return isValid
    }

}