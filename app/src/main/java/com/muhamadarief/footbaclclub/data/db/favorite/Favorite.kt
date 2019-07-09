package com.muhamadarief.footbaclclub.data.db.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "team_name") val teamName: String,
    @ColumnInfo(name = "team_logo") val teamLogo: String,
    @ColumnInfo(name = "team_description") val teamDescription: String,
    @ColumnInfo(name = "team_formed_year") val teamFormedYear: Int,
    @ColumnInfo(name = "team_fanart") val teamFanArt: String
)