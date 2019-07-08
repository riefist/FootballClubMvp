package com.muhamadarief.footbaclclub.ui.main

import com.muhamadarief.footbaclclub.data.responses.League
import com.muhamadarief.footbaclclub.ui.main.model.Team

interface MainContract {

    interface Presenter {
        fun onAttach(view: View)
        fun onDetach()
        fun getLeagues()
        fun getTeamsByLeague(leagueName: String)
    }

    interface View {

        fun showLoading(isLoading: Boolean)
        fun showLeagues(leagues: List<League>?)
        fun showError(msg: String)
        fun showTeams(teams: List<Team>)

    }

}