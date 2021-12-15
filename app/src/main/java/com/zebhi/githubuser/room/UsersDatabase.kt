package com.zebhi.githubuser.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zebhi.githubuser.room.table.UsersEntity

@Database(entities = [UsersEntity::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDAO

    companion object {
        private var instance: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase? {
            if (instance == null) {
                synchronized(UsersDatabase::class) {
                    instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            UsersDatabase::class.java,
                            "users_data.db"
                        )
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return instance
        }
    }
}
