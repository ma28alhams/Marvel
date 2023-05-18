package com.red_velvet.marvel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.red_velvet.marvel.data.local.daos.ComicDao
import com.red_velvet.marvel.data.local.entities.ComicEntity

@Database([ComicEntity::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun MarvelComicDao(): ComicDao
    abstract fun MarvelEventDao()
    abstract fun MarvelCharacterDao()

    companion object {
        private const val DATABASE_NAME = "note database"
        @Volatile
        var instance: MarvelDatabase? = null
        fun getInstance(context: Context): MarvelDatabase {
            return instance
                ?: synchronized(this) { buildDatabase(context = context).also { instance = it } }
        }

        fun buildDatabase(context: Context): MarvelDatabase {
            return Room.databaseBuilder(context, MarvelDatabase::class.java, DATABASE_NAME).build()

        }
    }
}