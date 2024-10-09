package com.droidcon.graphqlmaster.presentation.mutation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.CollegeRequestEntity
import com.droidcon.graphqlmaster.domain.usecase.CreateCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.UpdateCollegeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCollegeMutationScreenVM @Inject constructor(
    private val getCollegeUseCase: GetCollegeUseCase,
    private val updateCollegeUseCase: UpdateCollegeUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CollegeState())
    val state = _state.asStateFlow()

    init {
        fetchColleges()
    }

    private fun fetchColleges() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                colleges = getCollegeUseCase.execute(),
                isLoading = false
            ) }
        }
    }

    fun submitCollege(collegeRequestEntity: CollegeRequestEntity) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val updatedCollege = updateCollegeUseCase.execute(collegeRequestEntity)

            _state.update { currentState ->
                val updatedColleges = currentState.colleges.map { college ->
                    if (college.id == updatedCollege?.id) updatedCollege else college
                }
                currentState.copy(
                    colleges = updatedColleges,
                    isLoading = false
                )
            }
        }
    }

    data class CollegeState(
        val colleges: List<CollegeEntity> = emptyList(),
        val isLoading: Boolean = false,
        val college: CollegeEntity? = null
    )
}