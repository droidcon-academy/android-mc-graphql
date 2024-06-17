package com.droidcon.graphqlmaster.presentation

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidcon.graphqlmaster.domain.model.StudentEntity

@Composable
fun StudentScreen(
    data: List<StudentEntity>,
    isLoading: Boolean,
) {
    StudentScreenContent(data,isLoading )
}

@Composable
private fun StudentScreenContent(data: List<StudentEntity>, isLoading: Boolean) {

    Column {


        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
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
            } else {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Student List")
                        Text("Total: ${data.size}")
                    }
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(data.size) { index ->
                            val college = data[index]
                            StudentItem(
                                student = college,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)

                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
private fun StudentItem(
    student: StudentEntity,
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
                text = student.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = student.dob)
        }
    }
}
