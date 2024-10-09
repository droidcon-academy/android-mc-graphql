package com.droidcon.graphqlmaster.presentation.subscription

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidcon.graphqlmaster.component.PrimaryButton
import com.droidcon.graphqlmaster.domain.model.StudentEntity

@Composable
fun CollegeSubscriptionScreen(
    state: CollegeSubscriptionScreenVM.CollegeState,
    fetchCollege: (collegeId: Int) -> Unit,
) {
    CollegeSubscriptionScreenContent(state, fetchCollege)
}

@Composable
private fun CollegeSubscriptionScreenContent(
    state: CollegeSubscriptionScreenVM.CollegeState,
    fetchCollege: (collegeId: Int) -> Unit,
) {
    var collegeIdInput by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        if(state.isLoading) {
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
                modifier =  Modifier.fillMaxHeight(),
            ) {
                Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    text = "Enter College Id to subscribe")
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = collegeIdInput,
                    onValueChange = { newText ->
                        collegeIdInput = newText
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "College Id") },
                    placeholder ={ Text(text = "Enter college ID", textAlign = TextAlign.Center) },
                )

                Box(modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .width(84.dp)
                    .align(Alignment.End)) {
                    PrimaryButton(ctaText = "Submit", onClick = {
                        if(collegeIdInput.isNotEmpty() && collegeIdInput.isNotBlank()) {
                            fetchCollege(collegeIdInput.toInt())
                        }
                    })
                }

                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    state.college?.let {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(modifier = Modifier.padding(vertical = 4.dp),
                                text = "College details")
                            Column(
                                modifier = Modifier.fillMaxWidth()
                                    .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), shape = RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "${it.id}. ${it.name}",
                                    fontSize = 18.sp
                                )
                                Text(text = it.location)
                                Text(text = it.establishedYear)
                                Text(text = it.profileUrl)
                            }

                            Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                text = "Students")

                            LazyColumn(
                                modifier = Modifier.weight(1f)
                            ) {
                                items(it.studentEntity.size) { index ->
                                    val student = it.studentEntity[index]
                                    StudentItem(
                                        student = student,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }?:Text(text ="No Data Found")

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
    Column(
        modifier = modifier.padding(top = 4.dp, bottom = 4.dp, start = 16.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = "${student.id}. ${student.name}",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = student.dob)
        Text(text = student.gender)
        Text(text = student.profileUrl)
    }
}