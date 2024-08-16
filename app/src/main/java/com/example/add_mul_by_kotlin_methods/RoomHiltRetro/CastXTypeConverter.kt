package com.example.add_mul_by_kotlin_methods.RetrofitPro.Converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.Cast

class CastXTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCastXList(casts: List<Cast>?): String? {
        return gson.toJson(casts)
    }

    @TypeConverter
    fun toCastXList(castsJson: String?): List<Cast>? {
        if (castsJson.isNullOrEmpty()) {
            return null
        }
        val type = object : TypeToken<List<Cast>>() {}.type
        return gson.fromJson(castsJson, type)
    }
}
