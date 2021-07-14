package com.example.thediary

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PageRepository(private val pageDao:PageDao)
{

    val allPages: Flow<List<Page>> = pageDao.getAlphabetizedWords()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(page: Page) {
        pageDao.insert(page)
    }
}