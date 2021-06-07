package com.example.receiver.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.receiver.models.UsersPojo

@Database(entities = [UsersPojo::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {

    abstract fun getUsersDao(): UsersDao

    companion object{
        @Volatile private var mInstance: UsersDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = mInstance ?: synchronized(lock){
            mInstance ?: buildDatabase(context).also {
                mInstance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
            context.applicationContext,
            UsersDatabase::class.java,
            "noteDatabase").build()



    }
}