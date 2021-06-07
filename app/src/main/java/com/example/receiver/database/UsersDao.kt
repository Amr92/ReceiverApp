package com.example.receiver.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receiver.models.UsersPojo

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceivedUsersData(user: UsersPojo)

    @Query("SELECT * FROM users_table")
    fun getAllData(): LiveData<List<UsersPojo>>
}