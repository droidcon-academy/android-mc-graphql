package com.droidcon.graphqlmaster.presentation.subscription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.usecase.CreateCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetStudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollegeSubscriptionScreenVM @Inject constructor(
    private val getCollegeUseCase: GetCollegeUseCase,
    private val addCollegeUseCase: CreateCollegeUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(CollegeState())
    val state = _state.asStateFlow()

    init {
        fetchColleges(false)
    }

    private fun fetchColleges(isDelay: Boolean) {
        viewModelScope.launch {
            if(isDelay) {
                delay(1000)
            }
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                colleges = getCollegeUseCase.execute(),
                isLoading = false
            ) }
        }
    }

    fun selectCollege(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(
              //  selectedCountry = getStudentUseCase.execute(id)
            ) }
        }
    }

    fun submitCollege(collegeRequestDTO: CollegeRequestDTO) {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update {
                it.copy(
                    college = addCollegeUseCase.execute(collegeRequestDTO),
                    isLoading = false
                )
            }
        }
        fetchColleges(true)
    }

    fun dismissCollegeDialog() {
        _state.update { it.copy(
            college = null
        ) }
    }

    data class CollegeState(
        val colleges: List<CollegeEntity> = emptyList(),
        val isLoading: Boolean = false,
        val college: CollegeEntity? = null
    )
}