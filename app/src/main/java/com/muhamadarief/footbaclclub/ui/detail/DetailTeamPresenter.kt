package com.muhamadarief.footbaclclub.ui.detail

import com.muhamadarief.footbaclclub.data.network.ApiClient
import com.muhamadarief.footbaclclub.data.responses.TeamDetailResponse
import com.muhamadarief.footbaclclub.utils.mapper.getTeamDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTeamPresenter : DetailTeamContract.Presenter {

    private var mView : DetailTeamContract.View? = null

    override fun onAttach(view: DetailTeamContract.View) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    override fun getTeamDetail(teamId: String) {
        mView?.showLoading(true)
        ApiClient.create().getTeamDetailById(teamId)
            .enqueue(object: Callback<TeamDetailResponse>{
                /**
                 * Invoked when a network exception occurred talking to the server or when an unexpected
                 * exception occurred creating the request or processing the response.
                 */
                override fun onFailure(call: Call<TeamDetailResponse>, t: Throwable) {
                    mView?.showLoading(false)
                    mView?.showError(t.message.toString())
                }

                /**
                 * Invoked for a received HTTP response.
                 *
                 *
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call [Response.isSuccessful] to determine if the response indicates success.
                 */
                override fun onResponse(call: Call<TeamDetailResponse>, response: Response<TeamDetailResponse>) {
                    mView?.showLoading(false)
                    val teamDetailResponse = response.body() as TeamDetailResponse
                    mView?.showTeamDetail(getTeamDetail(teamDetailResponse.teams[0]))
                }

            })
    }


}