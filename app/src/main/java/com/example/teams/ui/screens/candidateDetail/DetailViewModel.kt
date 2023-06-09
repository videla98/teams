package com.example.teams.ui.screens.candidateDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teams.Graph
import com.example.teams.data.database.entities.Candidate
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: TeamsRepository = Graph.teamsRepository,
    private val idCandidate: Int
    ) : ViewModel() {
    private val _state: MutableStateFlow<Candidate> = MutableStateFlow(Candidate())
    val state: StateFlow<Candidate> get() = _state

    init {
        viewModelScope.launch {
            repository.selectApiCandidate(idCandidate).collect {
                _state.value = it
            }
        }
    }
}