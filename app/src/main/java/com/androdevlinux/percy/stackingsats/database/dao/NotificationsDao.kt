package com.androdevlinux.percy.stackingsats.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androdevlinux.percy.stackingsats.database.schema.NotificationsEntity

@Dao
interface NotificationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotificationDetails(details: NotificationsEntity?): Long?

    @Query("SELECT count(id) FROM Notifications where notificationId = :notificationId ")
    fun notificationIdExists(notificationId: String): Int

}