package com.muhamadarief.footbaclclub.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muhamadarief.footbaclclub.data.db.favorite.Favorite
import com.muhamadarief.footbaclclub.data.db.favorite.FavoriteDao

@Database(
    entities = [Favorite::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao() : FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {

            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "football_club_db"
                    ).build()
                }
            }

            return INSTANCE as AppDatabase

        }

    }

}