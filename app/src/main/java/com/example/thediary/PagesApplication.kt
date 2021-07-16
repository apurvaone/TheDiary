package com.example.thediary

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PagesApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { PageRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { PageRepository(database.pageDao()) }
}