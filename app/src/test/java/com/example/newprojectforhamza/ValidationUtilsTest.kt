package com.example.newprojectforhamza

import org.junit.Assert.*
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun `validateUsername returns true for valid username`() {
        assertTrue(ValidationUtils.isValidUsername("validUser"))
    }

    @Test
    fun `validateUsername returns false for short username`() {
        assertFalse(ValidationUtils.isValidUsername("ab"))
    }

    @Test
    fun `validateEmail returns true for valid email`() {
        assertTrue(ValidationUtils.isValidEmail("test@gmail.com"))
    }

    @Test
    fun `validateEmail returns false for invalid email`() {
        assertFalse(ValidationUtils.isValidEmail("invalid-email"))
    }

    @Test
    fun `validatePhone returns true for valid phone`() {
        assertTrue(ValidationUtils.isValidPhone("1234567890"))
    }

    @Test
    fun `validatePhone returns false for phone with letters`() {
        assertFalse(ValidationUtils.isValidPhone("123abc"))
    }

    @Test
    fun `validatePassword returns true for valid password`() {
        assertTrue(ValidationUtils.isValidPassword("ValidPass1*"))
    }

    @Test
    fun `validatePassword returns false for missing special char`() {
        assertFalse(ValidationUtils.isValidPassword("InvalidPass1"))
    }
}