package com.androdevlinux.percy.stackingsats.database.schema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = NotificationsEntity.TABLE_NAME, indices = [Index(value = ["notificationId"], unique = true)])
class NotificationsEntity {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @ColumnInfo
    var notificationId: String? = null

    @ColumnInfo
    var title: String? = null

    @ColumnInfo
    var body: String? = null

    @ColumnInfo
    var link: String? = null

    companion object {
        const val TABLE_NAME = "Notifications"
    }
}