package com.muhamadarief.footbaclclub.data.network

import com.muhamadarief.footbaclclub.data.responses.LeaguesResponse
import com.muhamadarief.footbaclclub.data.responses.TeamDetailResponse
import com.muhamadarief.footbaclclub.data.responses.TeamsResponse
import com.muhamadarief.footbaclclub.utils.ENDPOINT_ALL_LEAGUES
import com.muhamadarief.footbaclclub.utils.ENDPOINT_TEAMS
import com.muhamadarief.footbaclclub.utils.ENDPOINT_TEAM_DETAIL
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ENDPOINT_ALL_LEAGUES)
    fun getAllLeagues(): Call<LeaguesResponse>

    @GET(ENDPOINT_TEAMS)
    fun getTeamsByLeague(
        @Query("l") league: String
    ): Call<TeamsResponse>

    @GET(ENDPOINT_TEAM_DETAIL)
    fun getTeamDetailById(
        @Query("id") idTeam: String
    ): Call<TeamDetailResponse>

}