package com.example.receiver.database

import androidx.lifecycle.LiveData
import com.example.receiver.models.UsersPojo

class UsersRepository(private val usersDao: UsersDao) {

    suspend fun insertData(user: UsersPojo){
        usersDao.insertReceivedUsersData(user)
    }

    val getAllData: LiveData<List<UsersPojo>> = usersDao.getAllData()
}