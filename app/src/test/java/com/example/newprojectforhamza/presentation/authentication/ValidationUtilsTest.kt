package com.example.newprojectforhamza.presentation.authentication

import org.junit.Assert
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun `validateUsername returns true for valid username`() {
        Assert.assertTrue(ValidationUtils.isValidUsername("validUser"))
    }

    @Test
    fun `validateUsername returns false for short username`() {
        Assert.assertFalse(ValidationUtils.isValidUsername("ab"))
    }

    @Test
    fun `validateEmail returns true for valid email`() {
        Assert.assertTrue(ValidationUtils.isValidEmail("test@gmail.com"))
    }

    @Test
    fun `validateEmail returns false for invalid email`() {
        Assert.assertFalse(ValidationUtils.isValidEmail("invalid-email"))
    }

    @Test
    fun `validatePhone returns true for valid phone`() {
        Assert.assertTrue(ValidationUtils.isValidPhone("1234567890"))
    }

    @Test
    fun `validatePhone returns false for phone with letters`() {
        Assert.assertFalse(ValidationUtils.isValidPhone("123abc"))
    }

    @Test
    fun `validatePassword returns true for valid password`() {
        Assert.assertTrue(ValidationUtils.isValidPassword("ValidPass1*"))
    }

    @Test
    fun `validatePassword returns false for missing special char`() {
        Assert.assertFalse(ValidationUtils.isValidPassword("InvalidPass1"))
    }
}