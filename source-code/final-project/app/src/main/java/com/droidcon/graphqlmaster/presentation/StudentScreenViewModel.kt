package com.droidcon.graphqlmaster.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.data.dto.StudentRequestDTO
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import com.droidcon.graphqlmaster.domain.usecase.CreateCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.CreateStudentUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetStudentUseCase
import com.droidcon.graphqlmaster.domain.usecase.SubscribeToStudentAddedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentScreenViewModel @Inject constructor(
    private val getStudentUseCase: GetStudentUseCase,
    private val subscribeToStudentAddedUseCase: SubscribeToStudentAddedUseCase
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val _data = MutableStateFlow<List<StudentEntity>>(emptyList())
    val data: StateFlow<List<StudentEntity>> = _data

    init {
        fetchStudent()
        subscribeToStudentAdded(2)
    }

    private fun subscribeToStudentAdded(collegeId: Int) {
        viewModelScope.launch {
            subscribeToStudentAddedUseCase.execute(collegeId)
                .collect { student ->
                    _data.value += listOf(student)
                }
        }
    }

    private fun fetchStudent() {
        if (isLoading.value) return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getStudentUseCase.execute(2)

                _data.value += result

            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}