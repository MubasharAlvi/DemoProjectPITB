package com.example.newprojectforhamza.data.utils

import androidx.room.TypeConverter

/**
 * Persists `List<Int>` as a comma‑separated String: "28,12,16".
 */
class Converters {

    @TypeConverter
    fun listToString(list: List<Int>?): String? =
        list?.joinToString(",")

    @TypeConverter
    fun stringToList(value: String?): List<Int>? =
        value?.takeIf { it.isNotBlank() }
             ?.split(",")
             ?.mapNotNull { it.toIntOrNull() }
}
