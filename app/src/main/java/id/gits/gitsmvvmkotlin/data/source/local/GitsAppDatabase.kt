package id.gits.gitsmvvmkotlin.data.source.local

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import id.gits.gitsmvvmkotlin.data.model.LocalMessages
import id.gits.gitsmvvmkotlin.data.model.Messages
import id.gits.gitsmvvmkotlin.data.model.UserLogin
import id.gits.gitsmvvmkotlin.data.source.local.dao.MessagesDao
import id.gits.gitsmvvmkotlin.data.source.local.dao.UserDao


@Database(entities = [UserLogin::class, LocalMessages::class], version = 2)
abstract class GitsAppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun messagesDao(): MessagesDao

    companion object {

        @Volatile
        private var INSTANCE: GitsAppDatabase? = null

        fun getInstance(context: Context): GitsAppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        GitsAppDatabase::class.java, "Movie.db")
                        .addMigrations(MIGRATION_2_3)
                        .fallbackToDestructiveMigration()
                        .build()

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE movie ADD COLUMN last_update INTEGER")
            }
        }
    }
}