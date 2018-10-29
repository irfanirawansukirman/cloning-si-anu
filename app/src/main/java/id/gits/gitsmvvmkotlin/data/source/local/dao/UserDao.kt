package id.gits.gitsmvvmkotlin.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import id.gits.gitsmvvmkotlin.data.model.UserLogin

/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM userlogin")
    fun getUser(): UserLogin

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(data: UserLogin)

}