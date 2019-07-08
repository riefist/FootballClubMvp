package com.muhamadarief.footbaclclub.ui.main

import android.util.Log
import com.muhamadarief.footbaclclub.data.network.ApiClient
import com.muhamadarief.footbaclclub.data.responses.LeaguesResponse
import com.muhamadarief.footbaclclub.data.responses.TeamItem
import com.muhamadarief.footbaclclub.data.responses.TeamsResponse
import com.muhamadarief.footbaclclub.utils.mapper.getTeams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter : MainContract.Presenter {

    private var mView : MainContract.View? = null

    override fun onAttach(view: MainContract.View) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    override fun getLeagues() {
        mView?.showLoading(true)
        ApiClient.create().getAllLeagues()
            .enqueue(object: Callback<LeaguesResponse> {
                override fun onFailure(call: Call<LeaguesResponse>, t: Throwable) {
                    mView?.showError(t.message.toString())
                }

                override fun onResponse(call: Call<LeaguesResponse>, response: Response<LeaguesResponse>) {
                    mView?.showLeagues(response.body()?.leagues)
                }
            })
    }

    override fun getTeamsByLeague(leagueName: String) {
        mView?.showLoading(true)
        ApiClient.create().getTeamsByLeague(leagueName)
            .enqueue(object: Callback<TeamsResponse>{
                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    mView?.showLoading(false)
                    mView?.showError(t.message.toString())
                }

                override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                    mView?.showLoading(false)
                    val teamsResponse = response.body() as TeamsResponse
                    val teams : List<TeamItem> = teamsResponse.teams

                    mView?.showTeams(getTeams(teams))
                }

            })
    }

}