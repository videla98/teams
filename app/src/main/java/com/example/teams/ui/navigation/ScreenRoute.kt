package com.example.teams.ui.navigation

sealed class ScreenRoute(var route: String) {
    object CandidatesScreen : ScreenRoute("CandidateScreen")
    object CandidateDetailScreen : ScreenRoute("DetailScreen")
    object TeamsScreen : ScreenRoute("TeamsScreen")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
               append("/$arg")
            }
        }
    }
}
