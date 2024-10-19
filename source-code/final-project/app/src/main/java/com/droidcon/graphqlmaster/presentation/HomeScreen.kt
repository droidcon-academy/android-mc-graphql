package com.droidcon.graphqlmaster.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.droidcon.graphqlmaster.presentation.navhost.NavigationItem

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Query",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal= 16.dp, vertical = 4.dp)
                .clickable {
                    navController.navigate(NavigationItem.SingleResourceQuery.route)
                }

        ) {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Single Resource Query",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Get Single College information corresponding to college Id ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("college") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("query") },
                    )
                }
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal= 16.dp, vertical = 4.dp)
                .clickable {
                    navController.navigate(NavigationItem.MultipleResourceQuery.route)
                }

        ) {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Multiple Resource Query",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Get All Colleges",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("Multiple") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("college") },
                    )
                }
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal= 16.dp, vertical = 4.dp)
                .clickable {
                    navController.navigate(NavigationItem.FragmentQuery.route)
                }

        ) {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Fragment Query",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Get College and student information corresponding to college Id using student query fragment",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("college") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("query") },
                    )
                }
            }
        }

        Text(
            text = "Mutation",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal= 16.dp, vertical = 4.dp)
                .clickable {
                    navController.navigate(NavigationItem.AddCollegeMutation.route)
                }

        ) {
            Column( modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Add college mutation",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Add college and show in college list",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("Mutation") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("college") },
                    )
                }
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal= 16.dp, vertical = 4.dp)
                .clickable {
                    navController.navigate(NavigationItem.UpdateCollegeMutation.route)
                }

        ) {
            Column( modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Update college mutation",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Update college information",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("Mutation") },
                    )
                    AssistChip(
                        onClick = { },

                        label = { Text("college") },
                    )
                }
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal= 16.dp, vertical = 4.dp)
                .clickable {
                    navController.navigate(NavigationItem.DeleteCollegeMutation.route)
                }

        ) {
            Column( modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Delete college mutation",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Update college information",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("Mutation") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("college") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("Delete") },
                    )
                }
            }
        }


        Text(
            text = "Subscription",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    navController.navigate(NavigationItem.SubscribeToCollege.route)
                }

        ) {
            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Subscription",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Subscribe to college Id and update the student list real time when any student add in college",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("Subscription") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("College") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("Student") },
                    )
                }
            }
        }


        Text(
            text = "Pagination",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    navController.navigate(NavigationItem.PaginationCollege.route)
                }

        ) {
            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Pagination ",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Get the list of colleges using pagination",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("Advance") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("Pagination") },
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("College") },
                    )
                }
            }
        }

    }
}