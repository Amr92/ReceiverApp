package com.example.receiver.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UsersPojo(
    val name: String,
    val email: String,
    val userId: String,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

