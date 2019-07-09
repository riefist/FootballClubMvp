package com.muhamadarief.footbaclclub.utils

import android.content.Context
import com.muhamadarief.footbaclclub.data.db.AppDatabase
import com.muhamadarief.footbaclclub.data.db.favorite.FavoriteDao
import com.muhamadarief.footbaclclub.ui.detail.DetailTeamPresenter

object InjectorUtils {

    fun provideAppDatabase(context: Context)
            : AppDatabase = AppDatabase.getInstance(context)

    fun provideFavoriteDao(appDatabase: AppDatabase)
            : FavoriteDao = appDatabase.favoriteDao()

    fun provideDetailTeamPresenter(favoriteDao: FavoriteDao)
            : DetailTeamPresenter = DetailTeamPresenter(favoriteDao)

}