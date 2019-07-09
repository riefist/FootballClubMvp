package com.muhamadarief.footbaclclub.data.db.favorite

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAllFavorite() : Flowable<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favorite: Favorite) : Completable

    @Delete
    fun deleteFavorite(favorite: Favorite) : Completable

    @Update
    fun updateFavorite(favorite: Favorite) : Completable

}