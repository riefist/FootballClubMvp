package com.muhamadarief.footbaclclub.ui.detail

import com.muhamadarief.footbaclclub.data.db.favorite.Favorite

interface DetailTeamContract {

    interface Presenter {
        fun onAttach(view: View)
        fun onDetach()
        fun getTeamDetail(teamId: String)
        fun addTeamToFavorite(teamId: Int, teamName: String, teamLogo: String,
                              teamDesc: String, teamFormedYear: Int, teamFanArt: String)
        fun findTeamById(teamId: Int)
        fun deleteFromFavorite(favorite: Favorite)
    }

    interface View {
        fun showLoading(isLoading: Boolean)
        fun showTeamDetail(teamDetail: TeamDetail)
        fun showError(msg: String)
        fun addedFavoriteSuccessfully(msg: String)
        fun addedFavoriteFailed(msg: String)
        fun isTeamFavorited(favorites: List<Favorite>)
        fun deletedFavorite(msg: String)
    }

}