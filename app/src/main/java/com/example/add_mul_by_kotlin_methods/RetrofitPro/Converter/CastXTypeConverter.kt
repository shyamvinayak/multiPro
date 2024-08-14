package com.example.add_mul_by_kotlin_methods.RetrofitPro.Converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.CastX

class CastXTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCastXList(casts: List<CastX>?): String? {
        return gson.toJson(casts)
    }

    @TypeConverter
    fun toCastXList(castsJson: String?): List<CastX>? {
        if (castsJson.isNullOrEmpty()) {
            return null
        }
        val type = object : TypeToken<List<CastX>>() {}.type
        return gson.fromJson(castsJson, type)
    }
}
