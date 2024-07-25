package com.example.jetpackcompose.repository

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contact_table")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val name:String,
    val contact:String,
    val address:String,

)
