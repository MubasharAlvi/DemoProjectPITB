package com.example.newprojectforhamza.data.utils

import androidx.room.TypeConverter

class GenreIntListConverter {
    @TypeConverter
    fun fromList(list: List<Int>?): String? = list?.joinToString(",")

    @TypeConverter
    fun toList(data: String?): List<Int>? =
        data?.takeIf { it.isNotEmpty() }?.split(",")?.map { it.toInt() }
}