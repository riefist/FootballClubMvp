package com.muhamadarief.footbaclclub.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muhamadarief.footbaclclub.R
import com.muhamadarief.footbaclclub.data.db.favorite.Favorite
import com.muhamadarief.footbaclclub.utils.*
import kotlinx.android.synthetic.main.activity_detail_team.*

class TeamDetailActivity : AppCompatActivity(), DetailTeamContract.View {


    private lateinit var teamId : String
    private lateinit var presenter: DetailTeamPresenter

    private lateinit var teamDetail: TeamDetail

    private var teamFavorites: List<Favorite> = listOf()

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

        presenter.findTeamById(teamId.toInt())

        fab_favorite.setOnClickListener {
            if (teamFavorites.isNotEmpty()){
                presenter.deleteFromFavorite(teamFavorites[0])
            } else {
                presenter.addTeamToFavorite(
                    teamDetail.teamId.toInt(),
                    teamDetail.teamName,
                    teamDetail.teamLogo,
                    teamDetail.teamDesc,
                    teamDetail.teamFormedYear.toInt(),
                    teamDetail.teamFanArt
                )
            }
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

    override fun addedFavoriteSuccessfully(msg: String) {
        this.toast(msg)
        fab_favorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_24dp))
    }

    override fun addedFavoriteFailed(msg: String) {
        this.toast(msg)
    }

    override fun deletedFavorite(msg: String) {
        this.toast(msg)
        fab_favorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_24dp))
    }

    override fun showTeamDetail(teamDetail: TeamDetail) {
        this.teamDetail = teamDetail

        img_fanart.loadImageFromUrl(teamDetail.teamFanArt)
        txt_team_name.text = teamDetail.teamName
        txt_formed_year.text = teamDetail.teamFormedYear
        txt_team_description.text = teamDetail.teamDesc

        fab_favorite.visible()
    }

    override fun showError(msg: String) {

    }

    override fun isTeamFavorited(favorites: List<Favorite>) {

        teamFavorites = favorites

        if (favorites.isNotEmpty()){
            fab_favorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_24dp))
        } else {
            fab_favorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_24dp))
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
