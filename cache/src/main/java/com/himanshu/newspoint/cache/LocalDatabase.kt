package com.himanshu.newspoint.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vinners.newspoint.cache.dao.ProfileDao
import com.vinners.newspoint.cache.entities.CachedProfile
import com.vinners.newspoint.cache.typeConverter.DateTypeConverter

/**
 * Created by Himanshu on 6/22/2018.
 */

@Database(
    entities = [
        CachedProfile::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateTypeConverter::class
)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "truman-ms.db"
    }

    // The associated DAOs for the database
    abstract fun profileDao(): ProfileDao
}
