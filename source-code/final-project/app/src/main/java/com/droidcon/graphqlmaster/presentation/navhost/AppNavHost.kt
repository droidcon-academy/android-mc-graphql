package com.droidcon.graphqlmaster.presentation.navhost

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
import com.droidcon.graphqlmaster.presentation.HomeScreen
import com.droidcon.graphqlmaster.presentation.mutation.AddCollegeMutationScreen
import com.droidcon.graphqlmaster.presentation.mutation.AddCollegeMutationScreenVM
import com.droidcon.graphqlmaster.presentation.pagination.CollegePaginationScreen
import com.droidcon.graphqlmaster.presentation.pagination.CollegePaginationScreenViewModel
import com.droidcon.graphqlmaster.presentation.query.FragmentQueryScreen
import com.droidcon.graphqlmaster.presentation.query.FragmentQueryScreenVM
import com.droidcon.graphqlmaster.presentation.query.MultipleResourceQueryScreen
import com.droidcon.graphqlmaster.presentation.query.MultipleResourceQueryScreenVM
import com.droidcon.graphqlmaster.presentation.query.SingleResourceQueryScreen
import com.droidcon.graphqlmaster.presentation.query.SingleRespurceQueryScreenVM
import com.droidcon.graphqlmaster.presentation.subscription.CollegeSubscriptionScreen
import com.droidcon.graphqlmaster.presentation.subscription.CollegeSubscriptionScreenVM
import com.droidcon.graphqlmaster.presentation.subscription.StudentScreen
import com.droidcon.graphqlmaster.presentation.subscription.StudentScreenViewModel

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

        composable(NavigationItem.SingleResourceQuery.route) {
            val viewModel = hiltViewModel<SingleRespurceQueryScreenVM>()
            val state by viewModel.state.collectAsState()

            Column(modifier = Modifier) {
                SingleResourceQueryScreen(
                    state = state,
                    fetchCollege = viewModel::fetchCollege,
                )

            }
        }

        composable(NavigationItem.MultipleResourceQuery.route) {
            val viewModel = hiltViewModel<MultipleResourceQueryScreenVM>()
            val state by viewModel.state.collectAsState()
            Column(modifier = Modifier) {
                MultipleResourceQueryScreen(
                    state = state,
                )
            }
        }

        composable(NavigationItem.FragmentQuery.route) {
            val viewModel = hiltViewModel<FragmentQueryScreenVM>()
            val state by viewModel.state.collectAsState()
            Column(modifier = Modifier) {
                FragmentQueryScreen(
                    state = state,
                    fetchCollege = viewModel::fetchCollege,
                )
            }
        }

        composable(NavigationItem.MultipleResourceQuery.route) {
            val viewModel = hiltViewModel<MultipleResourceQueryScreenVM>()
            val state by viewModel.state.collectAsState()
            Column(modifier = Modifier) {
                MultipleResourceQueryScreen(
                    state = state,
                )
            }
        }

        composable(NavigationItem.Mutation.route) {
            val viewModel = hiltViewModel<AddCollegeMutationScreenVM>()
            val state by viewModel.state.collectAsState()

            Column(modifier = Modifier) {
                AddCollegeMutationScreen(
                    state = state,
                    onSubmitCollege = viewModel::submitCollege,
                )

            }
        }

        composable(NavigationItem.Subscription.route) {
            val viewModel = hiltViewModel<CollegeSubscriptionScreenVM>()
            val state by viewModel.state.collectAsState()

            Column(modifier = Modifier) {
                CollegeSubscriptionScreen(
                    state = state,
                    onSelectCollege = viewModel::selectCollege,
                    navController
                )

            }
        }

        composable(NavigationItem.Student.route+"/{collegeId}") {
                navBackStackEntry ->
            val viewModel: StudentScreenViewModel = hiltViewModel(navBackStackEntry)
            val data by viewModel.data.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()

            Column(modifier = Modifier) {
                StudentScreen(
                    data = data,
                    isLoading = isLoading
                )
            }
        }

        composable(NavigationItem.PAGINATIONCOLLEGE.route) {
            val viewModel = hiltViewModel<CollegePaginationScreenViewModel>()

            Column(modifier = Modifier) {
                CollegePaginationScreen(viewModel)
            }
        }
    }
}

enum class Screen {
    SINGLE_RESOURCE_QUERY,
    MULTIPLE_RESOURCE_QUERY,
    FRAGMENT_QUERY,
    MUTATION,
    SUBSCRIPTION,
    STUDENT,
    HOME,
    PAGINATION_COLLEGE,
}
sealed class NavigationItem(val route: String) {
    data object Mutation : NavigationItem(Screen.MUTATION.name)
    data object Subscription : NavigationItem(Screen.SUBSCRIPTION.name)
    data object SingleResourceQuery : NavigationItem(Screen.SINGLE_RESOURCE_QUERY.name)
    data object MultipleResourceQuery : NavigationItem(Screen.MULTIPLE_RESOURCE_QUERY.name)
    data object FragmentQuery : NavigationItem(Screen.FRAGMENT_QUERY.name)
    data object Student : NavigationItem(Screen.STUDENT.name) {
        fun createRoute(collegeId: Int) = Screen.STUDENT.name+"/$collegeId"
    }
    data object PAGINATIONCOLLEGE : NavigationItem(Screen.PAGINATION_COLLEGE.name)
    data object HOME : NavigationItem(Screen.HOME.name)
}