package com.droidcon.graphqlmaster.presentation.query

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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.droidcon.graphqlmaster.component.IconButton
import com.droidcon.graphqlmaster.component.PrimaryButton
import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.presentation.navhost.NavigationItem

@Composable
fun SingleResourceQueryScreen(
    state: SingleRespurceQueryScreenVM.CollegeState,
    fetchCollege: (collegeId: Int) -> Unit,
) {
    SingleResourceQueryScreenContent(state, fetchCollege)
}

@Composable
private fun SingleResourceQueryScreenContent(
    state: SingleRespurceQueryScreenVM.CollegeState,
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

                Box(modifier = Modifier.padding(16.dp).width(84.dp).align(Alignment.End)) {
                    PrimaryButton(ctaText = "Submit", onClick = {
                        if(collegeIdInput.isNotEmpty() && collegeIdInput.isNotBlank()) {
                            fetchCollege(collegeIdInput.toInt())
                        }
                    })
                }

                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    state.college?.let {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = it.id.toString())
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = it.name,
                                fontSize = 24.sp
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = it.location)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = it.establishedYear)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = it.profileUrl)
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }

                }
            }
        }
    }
}