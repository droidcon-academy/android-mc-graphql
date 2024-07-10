package com.droidcon.graphqlmaster.presentation

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavHostController
import com.droidcon.graphqlmaster.component.IconButton
import com.droidcon.graphqlmaster.component.PrimaryButton
import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.domain.model.CollegeEntity

@Composable
fun CollegeScreen(
    state: CollegeScreenViewModel.CollegeState,
    onSelectCollege: (code: Int) -> Unit,
    onSubmitCollege: (collegeRequestDTO: CollegeRequestDTO) -> Unit,
    navController: NavHostController
) {
    CollegeScreenContent(state, onSelectCollege,onSubmitCollege, navController)
}

@Composable
private fun CollegeScreenContent(
    state: CollegeScreenViewModel.CollegeState,
    onSelectCollege: (code: Int) -> Unit,
    onSubmitCollege: (collegeRequestDTO: CollegeRequestDTO) -> Unit,
    navController: NavHostController
) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        AddCollegeBottomSheet(onSubmitCollege) {
            showSheet = false
        }
    }

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
                                .clickable {
                                    navController.navigate(NavigationItem.STUDENT.createRoute(collegeId = college.id))
                                    onSelectCollege(college.id) }
                                .padding(16.dp)

                        )
                    }
                }
                Box(modifier = Modifier.padding(16.dp).align(Alignment.End)) {
                    IconButton{
                        showSheet = true
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCollegeBottomSheet(
    onSubmitCollege: (collegeRequestDTO: CollegeRequestDTO) -> Unit,
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        AddCollege(onSubmitCollege, onDismiss)
    }
}

@Composable
fun AddCollege(
    onSubmitCollege: (collegeRequestDTO: CollegeRequestDTO) -> Unit,
    onDismiss: () -> Unit
) {
    var nameInput by remember { mutableStateOf("") }
    var locationInput by remember { mutableStateOf("") }
    var yearInput by remember { mutableStateOf("") }

   Column {
       OutlinedTextField(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp, vertical = 8.dp),
           value = nameInput,
           onValueChange = { newText ->
               nameInput = newText
           },

           label = { Text(text = "College Name") },
           placeholder ={ Text(text = "Enter college name", textAlign = TextAlign.Center) },
       )
       Spacer(modifier = Modifier.height(12.dp))
       OutlinedTextField(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp, vertical = 8.dp),
           value = locationInput,
           onValueChange = { newText ->
               locationInput = newText
           },
           label = { Text(text = "College location") },
           placeholder ={ Text(text = "Enter College location", textAlign = TextAlign.Center) },
       )

       Spacer(modifier = Modifier.height(12.dp))
       OutlinedTextField(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp, vertical = 8.dp),
           value = yearInput,
           onValueChange = { newText ->
               yearInput = newText
           },
           label = { Text(text = "College establishment year") },
           placeholder ={ Text(text = "Enter College establishment year", textAlign = TextAlign.Center) },
       )
       Spacer(modifier = Modifier.height(16.dp))
       Spacer(modifier = Modifier.height(16.dp))
       Box(modifier = Modifier.padding(16.dp)) {
           PrimaryButton(ctaText = "Submit", onClick = {
                if(nameInput.isNotEmpty() && nameInput.isNotBlank() &&
                    yearInput.isNotEmpty() && yearInput.isNotBlank() &&
                    nameInput.isNotEmpty() && nameInput.isNotBlank()) {
                    onSubmitCollege(CollegeRequestDTO(
                        name= nameInput,
                        location = locationInput,
                        establishedYear = yearInput
                    ))
                    onDismiss()
                }
           })
       }

   }
}