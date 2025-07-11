package com.example.newprojectforhamza.presentation.authentication

object ValidationUtils {
    private val USERNAME_RULE = Regex("^[A-Za-z]{3,16}$")
    private val PHONE_RULE = Regex("^\\d{10,15}$")
    private val EMAIL_RULE = Regex("^[A-Za-z0-9+_.%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private val PASSWORD_RULE = Regex("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*[*#!\$]$")

    fun isValidUsername(username: String) = USERNAME_RULE.matches(username)

    fun isValidEmail(email: String) = EMAIL_RULE.matches(email)
    fun isValidPhone(phone: String) = PHONE_RULE.matches(phone)
    fun isValidPassword(password: String) = PASSWORD_RULE.matches(password)
}