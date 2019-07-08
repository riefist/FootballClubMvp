package com.muhamadarief.footbaclclub.ui.detail

interface DetailTeamContract {

    interface Presenter {
        fun onAttach(view: View)
        fun onDetach()
        fun getTeamDetail(teamId: String)
    }

    interface View {
        fun showLoading(isLoading: Boolean)
        fun showTeamDetail(teamDetail: TeamDetail)
        fun showError(msg: String)
    }
}