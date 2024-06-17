package com.droidcon.graphqlmaster.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.usecase.CreateCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetPaginationCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetStudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollegePaginationScreenViewModel @Inject constructor(
    private val getPaginationCollegeUseCase: GetPaginationCollegeUseCase
): ViewModel() {

    private val _data = MutableStateFlow<List<CollegeEntity>>(emptyList())
    val data: StateFlow<List<CollegeEntity>> = _data

    private val _isNextPageLoading = MutableStateFlow(false)
    val isNextPageLoading: StateFlow<Boolean> = _isNextPageLoading

    private var endCursor: Int = 0
    private var count: Int = 0

    companion object{
        const val LIMIT = 5
    }

    init {
        fetchColleges()
    }

    fun fetchColleges() {
        if (_isNextPageLoading.value) return

        viewModelScope.launch {
            _isNextPageLoading.value = true

            try {
                val result = getPaginationCollegeUseCase.execute(LIMIT, endCursor)

                _data.value += result?.collegeEntity!!

                endCursor = result.nextPage!!
                count = result.total
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isNextPageLoading.value = false
            }
        }
    }

}

