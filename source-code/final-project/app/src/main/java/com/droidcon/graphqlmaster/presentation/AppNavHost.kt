package com.droidcon.graphqlmaster.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationItem.HOME.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.HOME.route) {
            Column(modifier = Modifier) {
                HomeScreen(navController)
            }
        }

        composable(NavigationItem.COLLEGE.route) {
            val viewModel = hiltViewModel<CollegeScreenViewModel>()
            val state by viewModel.state.collectAsState()

            Column(modifier = Modifier) {
                 CollegeScreen(
                    state = state,
                    onSelectCollege = viewModel::selectCollege,
                    onSubmitCollege = viewModel::submitCollege,
                    navController
                )

            }
        }

        composable(NavigationItem.PAGINATIONCOLLEGE.route) {
            val viewModel = hiltViewModel<CollegePaginationScreenViewModel>()

            Column(modifier = Modifier) {
                CollegePaginationScreen(viewModel)
            }
        }


        composable(NavigationItem.STUDENT.route) {
            val viewModel = hiltViewModel<StudentScreenViewModel>()
            val data by viewModel.data.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()
            Column(modifier = Modifier) {
                StudentScreen(
                    data = data,
                            isLoading = isLoading
                )
            }
        }
    }
}

enum class Screen {
    COLLEGE,
    STUDENT,
    HOME,
    PAGINATION_COLLEGE,
}
sealed class NavigationItem(val route: String) {
    data object COLLEGE : NavigationItem(Screen.COLLEGE.name)
    data object STUDENT : NavigationItem(Screen.STUDENT.name)
    data object PAGINATIONCOLLEGE : NavigationItem(Screen.PAGINATION_COLLEGE.name)
    data object HOME : NavigationItem(Screen.HOME.name)
}