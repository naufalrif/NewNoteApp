package com.example.newnoteapp.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User) : Long
    @Query("SELECT username FROM User WHERE User.email = :email AND User.password = :password")
    fun loginCheck(email : String, password : String) : String
}