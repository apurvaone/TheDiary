package com.example.thediary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Page::class), version = 1, exportSchema = false)

public abstract class PageRoomDatabase:RoomDatabase()
{
    abstract fun pageDao(): PageDao

    private class PageDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.pageDao())
                }
            }
        }

        suspend fun populateDatabase(pageDao: PageDao) {
            // Delete all content here.
            pageDao.deleteAll()

            // Add sample words.
            var page = Page("My first diary page"," this was amazing day i started python today as i plan to shift to python in full pace, i took a small pythin course from mosh and loving it also i am enjoyig android development")
            pageDao.insert(page)
            page = Page("Enjoyig the diary writing", "I have fallen in love with diary writing it gives great feeling of satisfaction and containment also it makes sure that i remain in good frame of mind")
            pageDao.insert(page)

            // TODO: Add your own words!
        }
    }


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PageRoomDatabase? = null

        fun getDatabase(context: Context, scope:CoroutineScope): PageRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PageRoomDatabase::class.java,
                    "page_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}