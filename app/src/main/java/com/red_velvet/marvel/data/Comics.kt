package com.red_velvet.marvel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("COMICS_TABLE")
data class Comics(
    @PrimaryKey val id :Int,
    val title :String,
    val img :String,
)
