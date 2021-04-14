package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.RoomConstants

@Database(entities = [DBMovie::class], version = RoomConstants.DB_VERSION)
abstract class DBMovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): DBMovieDAO

    companion object {

        private var db_instance: DBMovieRoomDatabase? = null

        fun getDataBaseInstance(application: Application): DBMovieRoomDatabase {

            if (db_instance != null) {
                return db_instance!!
            }

            db_instance = Room.databaseBuilder(
                    application,
                    DBMovieRoomDatabase::class.java,
                    RoomConstants.DB_NAME
            ).fallbackToDestructiveMigration()
                    .build()

            return db_instance!!
        }

    }

}