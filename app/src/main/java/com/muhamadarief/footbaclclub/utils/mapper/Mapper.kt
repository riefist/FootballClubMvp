package com.muhamadarief.footbaclclub.utils.mapper

import com.muhamadarief.footbaclclub.data.responses.TeamItem
import com.muhamadarief.footbaclclub.ui.detail.TeamDetail
import com.muhamadarief.footbaclclub.ui.main.model.Team

fun getTeams(teams: List<TeamItem>): List<Team> {

    val list = mutableListOf<Team>()

    teams.forEach {
        list.add(Team(it.idTeam, it.strTeam, it.strTeamBadge))
    }

    return list
}

fun getTeamDetail(teamItem: TeamItem): TeamDetail {
    return TeamDetail(
        teamItem.idTeam,
        teamItem.strTeam,
        teamItem.strTeamFanart1,
        teamItem.intFormedYear,
        teamItem.strDescriptionEN,
        teamItem.strTeamBadge
    )
}