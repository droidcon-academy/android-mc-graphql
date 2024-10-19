package com.droidcon.graphqlmaster.presentation.mutation

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidcon.graphqlmaster.component.DeleteIconButton
import com.droidcon.graphqlmaster.component.EditIconButton
import com.droidcon.graphqlmaster.component.IconButton
import com.droidcon.graphqlmaster.component.PrimaryButton
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.CollegeRequestEntity

@Composable
fun DeleteCollegeMutationScreen(
    state: DeleteCollegeMutationScreenVM.CollegeState,
    onDeleteCollege: (collegeId: Int) -> Unit,
) {
    DeleteCollegeMutationScreenContent(state,onDeleteCollege)
}

@Composable
private fun DeleteCollegeMutationScreenContent(
    state: DeleteCollegeMutationScreenVM.CollegeState,
    onDeleteCollege: (collegeId: Int) -> Unit,
) {

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
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("College List")
                    Text("Total: ${state.colleges.size}")
                }
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {

                    items(state.colleges.size) { index ->
                        val college = state.colleges[index]
                        CollegeItem(
                            college = college,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onDeleteCollege = onDeleteCollege
                        )
                    }
                }
            }
        }
    }
}
@Composable
private fun CollegeItem(
    college: CollegeEntity,
    modifier: Modifier = Modifier,
    onDeleteCollege: (collegeId: Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = college.name,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = college.location)

        Spacer(modifier = Modifier.width(16.dp))
        Text(text = college.establishedYear)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            DeleteIconButton{
                onDeleteCollege(college.id)
            }
        }
    }
}