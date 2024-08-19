// AppDatabase.kt
package com.geeklord.jobsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeklord.jobsapp.Models.JobsEntity

@Database(entities = [JobsEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): JobsDao
}
