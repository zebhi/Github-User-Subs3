package com.zebhi.githubuser.room

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.zebhi.githubuser.room.table.UsersEntity

@Dao
interface UsersDAO {

    @Query("SELECT * FROM users_table")
    fun getAllUsers(): List<UsersEntity>

    @Query("SELECT * FROM users_table WHERE id = :id LIMIT 1")
    fun getUser(id: Int?): UsersEntity

    @Query("DELETE FROM users_table WHERE id = :id")
    fun deleteUser(id: Int?): Int

    @Insert(onConflict = REPLACE)
    fun insertUserFavorite(usersEntity: UsersEntity): Long

    @Delete
    fun deleteUserFav(usersEntity: UsersEntity)

    @Query("SELECT * FROM users_table")
    fun getUsersProvider(): Cursor

}
