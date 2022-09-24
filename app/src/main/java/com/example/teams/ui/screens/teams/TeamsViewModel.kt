package com.example.teams.ui.screens.teams

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teams.Graph
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.model.entities.Team
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.log

class TeamsViewModel(
    private val repository: TeamsRepository = Graph.teamsRepository
    ) : ViewModel() {
    private val _state: MutableState<List<TeamWithMembers>> =
        mutableStateOf(emptyList())
    val state: State<List<TeamWithMembers>> get() = _state

    private val allTeams = repository.selectAllTeams()

    init {
        fetchTeams()
    }

    private fun fetchTeams() {
        viewModelScope.launch {
            allTeams.map {
                it.map {
                    async { TeamWithMembers(it, repository.selectCandidatesOfTeam(it.id).first()) }
                }.awaitAll()
            }.collect {
                _state.value = it
            }
            //for (team in it) {
            //repository.selectCandidatesOfTeam(team.id).collect{ candidates ->
            //_state.value.add(TeamWithMembers(team, candidates))
            //}
            //}
        }
    }

    suspend fun updateNull(id: Int, idTeam: Int) {
        repository.updateNullTeam(id, idTeam)
        fetchTeams()
    }

    suspend fun insert(team: Team) {
        repository.insertTeam(team)
        fetchTeams()
    }

    data class TeamWithMembers(
        val team: Team,
        val candidates: List<Candidate>?,
    )
}

