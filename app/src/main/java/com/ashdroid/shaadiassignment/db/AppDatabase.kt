package com.ashdroid.shaadiassignment.db

import androidx.room.*
import com.ashdroid.shaadiassignment.db.converters.MatchProfileConverter
import com.ashdroid.shaadiassignment.db.daos.MatchProfileDao
import com.ashdroid.shaadiassignment.db.models.MatchProfileEntity

@Database(entities = [MatchProfileEntity::class], version = 1)
@TypeConverters(MatchProfileConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchProfileDao(): MatchProfileDao
}