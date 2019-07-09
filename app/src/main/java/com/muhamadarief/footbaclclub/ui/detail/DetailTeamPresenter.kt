package com.muhamadarief.footbaclclub.ui.detail

import android.util.Log
import com.muhamadarief.footbaclclub.data.db.favorite.Favorite
import com.muhamadarief.footbaclclub.data.db.favorite.FavoriteDao
import com.muhamadarief.footbaclclub.data.network.ApiClient
import com.muhamadarief.footbaclclub.data.responses.TeamDetailResponse
import com.muhamadarief.footbaclclub.utils.mapper.getTeamDetail
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTeamPresenter(val favoriteDao: FavoriteDao) : DetailTeamContract.Presenter {


    private var mView : DetailTeamContract.View? = null
    private lateinit var disposables: CompositeDisposable

    override fun onAttach(view: DetailTeamContract.View) {
        mView = view
        disposables = CompositeDisposable()
    }

    override fun onDetach() {
        mView = null
        disposables.clear()
    }

    override fun getTeamDetail(teamId: String) {
        mView?.showLoading(true)
        ApiClient.create().getTeamDetailById(teamId)
            .enqueue(object: Callback<TeamDetailResponse>{
                override fun onFailure(call: Call<TeamDetailResponse>, t: Throwable) {
                    mView?.showLoading(false)
                    mView?.showError(t.message.toString())
                }

                override fun onResponse(call: Call<TeamDetailResponse>, response: Response<TeamDetailResponse>) {
                    mView?.showLoading(false)
                    val teamDetailResponse = response.body() as TeamDetailResponse
                    mView?.showTeamDetail(getTeamDetail(teamDetailResponse.teams[0]))
                }

            })
    }

    override fun addTeamToFavorite(
        teamId: Int,
        teamName: String,
        teamLogo: String,
        teamDesc: String,
        teamFormedYear: Int,
        teamFanArt: String
    ) {
        val favorite = Favorite(teamId, teamName, teamLogo, teamDesc,
            teamFormedYear, teamFanArt)

        disposables.add(
            Completable.fromAction { favoriteDao.addFavorite(favorite) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    // jika berhasil insert
                    mView?.addedFavoriteSuccessfully("Berhasil menambahkan team ke Favorite")
                },{
                    // jika insert gagal
                    Log.d("DetailTeamPresenter", it.message.toString())
                    mView?.addedFavoriteFailed(it.message.toString())
                })
        )
    }

    override fun findTeamById(teamId: Int) {
        disposables.add(
            favoriteDao.findTeamById(teamId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mView?.isTeamFavorited(it)
                },{
                    Log.d("DetailTeamPresenter", it.message.toString())
                })
        )
    }

    override fun deleteFromFavorite(favorite: Favorite) {
        disposables.add(
            Completable.fromAction { favoriteDao.deleteFavorite(favorite) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    // jika berhasil delete
                    mView?.deletedFavorite("Hapus favorite berhasil")
                },{
                    // jika delete gagal
                    mView?.showError(it.message.toString())
                })
        )
    }

}