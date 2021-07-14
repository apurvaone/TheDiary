package com.example.thediary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page_table")
data class Page (@PrimaryKey @ColumnInfo(name = "title") val title:String, @ColumnInfo(name = "content") val content:String){
}