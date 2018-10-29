package id.gits.gitsmvvmkotlin.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import id.gits.gitsmvvmkotlin.data.model.LocalMessages
import io.reactivex.Single

/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
@Dao
interface MessagesDao {

    @Query("SELECT * FROM localMessages")
    fun getMessages(): Single<List<LocalMessages>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMessages(data: LocalMessages)

}