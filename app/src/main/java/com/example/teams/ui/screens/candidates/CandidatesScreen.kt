package com.example.teams.ui.screens.candidates

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.teams.data.model.entities.Candidate
import com.example.teams.ui.screens.shared.EmployeeCard

@Preview(showBackground = true)
@Composable
fun Candidates() {
    LazyColumn {
       items(10) { EmployeeCard() }
    }
}

@Composable
fun CandidatesScreen(viewModel: CandidatesViewModel) {
    LazyColumn {
    }
}