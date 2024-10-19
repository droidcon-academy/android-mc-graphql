package com.droidcon.graphqlmaster.presentation.mutation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.CollegeRequestEntity
import com.droidcon.graphqlmaster.domain.usecase.CreateCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.DeleteCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeByCollegeIdUseCase
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
class DeleteCollegeMutationScreenVM @Inject constructor(
    private val getCollegeUseCase: GetCollegeUseCase,
    private val deleteCollegeUseCase: DeleteCollegeUseCase
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

    fun deleteCollege(collegeId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val deleteSuccess = deleteCollegeUseCase.execute(collegeId)

            if (deleteSuccess) {
                _state.update { currentState ->
                    val updatedColleges = currentState.colleges.filter { college ->
                        college.id != collegeId
                    }
                    currentState.copy(
                        colleges = updatedColleges,
                        isLoading = false
                    )
                }
            } else {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    data class CollegeState(
        val colleges: List<CollegeEntity> = emptyList(),
        val isLoading: Boolean = false,
        val college: CollegeEntity? = null
    )
}