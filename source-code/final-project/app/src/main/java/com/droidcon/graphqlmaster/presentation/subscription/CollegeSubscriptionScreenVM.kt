package com.droidcon.graphqlmaster.presentation.subscription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.usecase.GetFragmentStudentByCollegeIdUseCase
import com.droidcon.graphqlmaster.domain.usecase.SubscribeToCollegeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollegeSubscriptionScreenVM @Inject constructor(
    private val useCase: GetFragmentStudentByCollegeIdUseCase,
    private val subscribeToAdUseCase: SubscribeToCollegeUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(CollegeState())
    val state = _state.asStateFlow()

    fun fetchCollege(collegeId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                college = useCase.execute(collegeId),
                isLoading = false
            ) }
        }

        subscribeToCollege(collegeId)
    }

    private fun subscribeToCollege(collegeId: Int) {
        viewModelScope.launch {
            subscribeToAdUseCase.execute(collegeId)
                .collect { student ->
                    _state.update { currentState ->
                        val updatedCollege = currentState.college?.copy(
                            studentEntity = currentState.college.studentEntity + student
                        )
                        currentState.copy(college = updatedCollege)
                    }
                }
        }
    }

    data class CollegeState(
        val isLoading: Boolean = false,
        val college: CollegeEntity? = null
    )
}