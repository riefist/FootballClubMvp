package com.muhamadarief.footbaclclub.data.responses

data class LeaguesResponse(
    val leagues: List<League>
)

data class League(
    val idLeague: String,
    val strLeague: String,
    val strLeagueAlternate: String,
    val strSport: String
)