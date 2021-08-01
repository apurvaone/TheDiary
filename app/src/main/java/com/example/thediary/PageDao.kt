package com.example.thediary

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PageDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(page: Page)

    @Query("DELETE FROM page_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM page_table ")
    fun getAlphabetizedWords(): Flow<List<Page>>

     @Update
     fun update(page: Page)




}