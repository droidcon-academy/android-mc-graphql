package com.droidcon.graphqlmaster.presentation.pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidcon.graphqlmaster.domain.model.CollegeEntity

@Composable
fun CollegePaginationScreen(viewModel: CollegePaginationScreenViewModel) {
    CollegeScreenPaginationContent(viewModel)
}

@Composable
private fun CollegeScreenPaginationContent(viewModel: CollegePaginationScreenViewModel) {

    val data by viewModel.data.collectAsState()
    val isNextLoading by viewModel.isNextPageLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier =  Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("College List")
                    Text("Total: ${data.size}")
                }
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(data) { index, item ->
                        CollegeItem(
                            college = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                        if (index == data.size - 1 && !isNextLoading) {
                            viewModel.fetchColleges()
                        }
                    }

                    if (isNextLoading) {
                        item {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(32.dp),
                                    color = Color.LightGray,
                                    strokeWidth = 5.dp
                                )
                            }
                        }
                    }
                }
            }
    }
}
@Composable
private fun CollegeItem(
    college: CollegeEntity,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = college.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = college.location)
        }
    }
}