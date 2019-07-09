package com.muhamadarief.footbaclclub.data.db.favorite

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FavoriteDao {

    //TODO kerjakan get all teams
    @Query("SELECT * FROM favorite")
    fun getAllFavorite() : Flowable<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE id = :teamId")
    fun findTeamById(teamId: Int) : Flowable<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favorite: Favorite)

    @Delete
    fun deleteFavorite(favorite: Favorite)

    @Update
    fun updateFavorite(favorite: Favorite)

}