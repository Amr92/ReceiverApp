package com.example.receiver.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.receiver.database.UsersDatabase
import com.example.receiver.database.UsersRepository
import com.example.receiver.models.UsersPojo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UsersRepository
    val getAllData: LiveData<List<UsersPojo>>

    init {
        val dao = UsersDatabase.invoke(application).getUsersDao()
        repository = UsersRepository(dao)
        getAllData = repository.getAllData
    }

    fun insertData(user: UsersPojo){

        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(user)
        }

    }
}