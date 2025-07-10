package com.example.newprojectforhamza.presentation.ui.authentication

import android.content.Intent
import android.os.Bundle
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
import com.example.newprojectforhamza.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.presentation.ui.authentication.viewmodels.UserAuthViewModel
import com.example.newprojectforhamza.presentation.ui.dashboard.MainActivity
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import kotlinx.coroutines.launch
import kotlin.getValue


@AndroidEntryPoint
class UserLoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

   private  var token : Boolean?=false
    private val vm: UserAuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vm.moviesState.collect { state ->
                        when (state) {
                            is ResourceApiState.Loading -> binding.progressbarId.visibility = View.GONE
                            is ResourceApiState.Success -> {
                                binding.progressbarId.visibility = View.GONE
                                val data = state.data?.map { token = it.success } ?: return@collect
                                if (validateAndLogin("validUser", "ValidPass1*") && token==true) {
                                    startActivity(Intent(requireContext(), MainActivity::class.java))
                                    requireActivity().finish()
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

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text?.toString()?.trim().orEmpty()
            val password = binding.etPassword.text?.toString().orEmpty()
            vm.authLogin(userName = username, password = password)

        }


        binding.userRegister.setOnClickListener {
            findNavController().navigate(R.id.action_userLoginFragment_to_userRegisterFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    /**  Returns true → navigate; false → stay on this screen  */
     fun validateAndLogin(string: String, string1: String): Boolean {
        val username = binding.etUsername.text?.toString()?.trim().orEmpty()
        val password = binding.etPassword.text?.toString().orEmpty()

        val USERNAME_RULE = Regex("^[A-Za-z]{3,16}$")
        val PASSWORD_RULE =
            Regex("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*[*#!\$]$")

        val usernameValid = USERNAME_RULE.matches(username)
        val passwordValid = PASSWORD_RULE.matches(password)

        binding.usernameInputLayout.error =
            if (usernameValid) null else getString(R.string.invalid_username)
        binding.passwordInputLayout.error =
            if (passwordValid) null else getString(R.string.invalid_password)

        if (!usernameValid || !passwordValid) {
            val msg = when {
                !usernameValid && !passwordValid -> R.string.invalid_username_and_password
                !usernameValid                  -> R.string.invalid_username
                else                            -> R.string.invalid_password
            }
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}