package com.muhamadarief.footbaclclub.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muhamadarief.footbaclclub.R
import com.muhamadarief.footbaclclub.utils.*
import kotlinx.android.synthetic.main.activity_detail_team.*

class TeamDetailActivity : AppCompatActivity(), DetailTeamContract.View {

    private lateinit var teamId : String
    private lateinit var presenter: DetailTeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        supportActionBar?.title = getString(R.string.team_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // get teamId from intent
        teamId = intent.getStringExtra(EXTRA_TEAM_ID)

        val appDatabase = InjectorUtils.provideAppDatabase(this)
        val favoriteDao = InjectorUtils.provideFavoriteDao(appDatabase)
        presenter = InjectorUtils.provideDetailTeamPresenter(favoriteDao)

        presenter.onAttach(this)

        presenter.getTeamDetail(teamId)

        fab_favorite.setOnClickListener {
            presenter.addTeamToFavorite()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun showLoading(isLoading: Boolean) {
        if (isLoading){
            progressBar.visible()
        } else {
            progressBar.gone()
        }
    }

    override fun showTeamDetail(teamDetail: TeamDetail) {
        img_fanart.loadImageFromUrl(teamDetail.teamFanArt)
        txt_team_name.text = teamDetail.teamName
        txt_formed_year.text = teamDetail.teamFormedYear
        txt_team_description.text = teamDetail.teamDesc
    }

    override fun showError(msg: String) {

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
