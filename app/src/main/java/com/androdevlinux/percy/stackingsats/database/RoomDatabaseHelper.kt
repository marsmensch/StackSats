package com.androdevlinux.percy.stackingsats.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androdevlinux.percy.stackingsats.database.dao.NotificationsDao
import com.androdevlinux.percy.stackingsats.database.schema.NotificationsEntity

@Database(entities = [NotificationsEntity::class], version = 1)
abstract class RoomDatabaseHelper : RoomDatabase() {
    abstract fun notificationTableDao(): NotificationsDao?
    companion object {
        // Database name to be used
        private const val databaseName = "StackSatsDB"

        @Volatile
        private var INSTANCE: RoomDatabaseHelper? = null


        fun getInstance(context: Context): RoomDatabaseHelper {

            synchronized(this) {
                var instance =
                    INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDatabaseHelper::class.java,
                        databaseName
                    ).fallbackToDestructiveMigration().build()
                    // Assign INSTANCE to the newly created database.
                    // Add migration logic and call it according to versions .addMigrations(MIGRATION_1_2)
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}