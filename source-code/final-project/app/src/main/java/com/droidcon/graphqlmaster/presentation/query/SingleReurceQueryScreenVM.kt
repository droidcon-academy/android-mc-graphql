package com.droidcon.graphqlmaster.presentation.query

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeByCollegeIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleRespurceQueryScreenVM @Inject constructor(
    private val useCase: GetCollegeByCollegeIdUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(CollegeState())
    val state = _state.asStateFlow()

    fun fetchCollege(collegeId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                college = useCase.execute(collegeId = collegeId),
                isLoading = false
            ) }
        }
    }


    data class CollegeState(
        val isLoading: Boolean = false,
        val college: CollegeEntity? = null
    )
}