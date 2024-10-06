package com.droidcon.graphqlmaster.presentation.query

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultipleResourceQueryScreenVM @Inject constructor(
    private val getCollegeUseCase: GetCollegeUseCase,
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

    data class CollegeState(
        val colleges: List<CollegeEntity> = emptyList(),
        val isLoading: Boolean = false,
    )
}