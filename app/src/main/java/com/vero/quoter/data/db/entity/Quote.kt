package com.vero.quoter.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val quote: String,
    var liked: Boolean
)