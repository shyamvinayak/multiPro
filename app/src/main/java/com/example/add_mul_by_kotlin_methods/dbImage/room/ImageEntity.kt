package com.example.add_mul_by_kotlin_methods.dbImage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "imageData", typeAffinity = ColumnInfo.BLOB)
    val imageData : ByteArray
)