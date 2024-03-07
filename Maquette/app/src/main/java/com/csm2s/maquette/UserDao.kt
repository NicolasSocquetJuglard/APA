package com.csm2s.maquette

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>
    @Query("SELECT * FROM users WHERE uid IN (:idList)")
    fun loadUsersByIds(idList: IntArray): List<User>
    @Query("SELECT * FROM users WHERE first_name LIKE :first AND "+"last_name LIKE :last LIMIT 1")
    fun findUserByName(first: String, last: String): User
    @Insert
    fun insertUsers(vararg users: User)
    @Delete
    fun deleteUser(user: User)
    @Update
    fun updateUser(user: User)
}