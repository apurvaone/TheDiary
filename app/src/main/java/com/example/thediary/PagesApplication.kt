package com.example.thediary

import android.app.Application

class PagesApplication: Application() {

    val database by lazy { PageRoomDatabase.getDatabase(this) }
    val repository by lazy { PageRepository(database.pageDao()) }
}