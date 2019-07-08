package com.muhamadarief.footbaclclub.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhamadarief.footbaclclub.R
import com.muhamadarief.footbaclclub.data.network.ApiService
import com.muhamadarief.footbaclclub.data.responses.League
import com.muhamadarief.footbaclclub.ui.detail.TeamDetailActivity
import com.muhamadarief.footbaclclub.ui.main.model.Team
import com.muhamadarief.footbaclclub.utils.EXTRA_TEAM_ID
import com.muhamadarief.footbaclclub.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainPresenter
    private val leaguesName = mutableListOf<String>()

    private lateinit var teamAdapter: TeamAdapter
    private val teams = mutableListOf<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter()
        presenter.onAttach(this)
        presenter.getLeagues()


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getTeamsByLeague(leaguesName[position])
            }

        }


        //inisiasi adapter
        teamAdapter = TeamAdapter(teams){
            // when item teams clicked then
            // goto detail activity with teamId
            val intent = Intent(this, TeamDetailActivity::class.java)
            intent.putExtra(EXTRA_TEAM_ID, it.teamId)
            startActivity(intent)
        }


        //inisiasi recyclerview
        rv_teams.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = teamAdapter
        }

    }

    override fun showLoading(isLoading: Boolean) {
        swipe_refresh.isRefreshing = isLoading
    }

    override fun showLeagues(leagues: List<League>?) {
        if (leagues != null){
            leagues.forEach { league ->
                leaguesName.add(league.strLeague)
            }

            setDataSpinner(leaguesName)
        }
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun showTeams(teams: List<Team>) {
        if (this.teams.isNotEmpty()) this.teams.clear()
        this.teams.addAll(teams)
        teamAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    fun setDataSpinner(data: List<String>){
        val adapter = ArrayAdapter(
            this,
            R.layout.item_row_leagues,
            data
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

}
