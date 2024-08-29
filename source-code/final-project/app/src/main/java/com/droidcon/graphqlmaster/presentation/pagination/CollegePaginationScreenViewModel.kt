package com.droidcon.graphqlmaster.presentation.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.usecase.GetPaginationCollegeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        if (_isNextPageLoading.value || (count!=endCursor && count< endCursor)) return

        viewModelScope.launch {
            _isNextPageLoading.value = true

            try {
                val result = getPaginationCollegeUseCase.execute(LIMIT, endCursor)
                _data.value += result?.collegeEntity!!

                endCursor = result.nextPage!!
                count = result.total
            } catch (e: Exception) {
                _isNextPageLoading.value = false
            } finally {
                _isNextPageLoading.value = false
            }
        }
    }

}

