package com.muhamadarief.footbaclclub.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhamadarief.footbaclclub.R
import com.muhamadarief.footbaclclub.ui.main.model.Team
import com.muhamadarief.footbaclclub.utils.invis
import com.muhamadarief.footbaclclub.utils.loadImageFromUrl
import kotlinx.android.synthetic.main.item_row_teams.view.*

class TeamAdapter(private val teams: List<Team>,
                  private val listener: (Team) -> Unit ) : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_teams, parent, false)
        return TeamViewHolder(view)
    }


    override fun getItemCount(): Int = teams.size


    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }
}

class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(team: Team,
                 listener: (Team) -> Unit)= with(itemView){

        txt_team.text = team.teamName

        img_team.loadImageFromUrl(team.teamLogo)

        setOnClickListener { listener(team) }
    }

}