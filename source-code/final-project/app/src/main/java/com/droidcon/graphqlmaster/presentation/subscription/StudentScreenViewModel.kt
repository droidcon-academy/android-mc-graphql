package com.droidcon.graphqlmaster.presentation.subscription

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import com.droidcon.graphqlmaster.domain.usecase.GetStudentByCollegeIdUseCase
import com.droidcon.graphqlmaster.domain.usecase.SubscribeToStudentAddedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getStudentByCollegeIdUseCase: GetStudentByCollegeIdUseCase,
    private val subscribeToStudentAddedUseCase: SubscribeToStudentAddedUseCase,
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val _data = MutableStateFlow<List<StudentEntity>>(emptyList())
    val data: StateFlow<List<StudentEntity>> = _data

    init {
        val arg1: String? = savedStateHandle["collegeId"]
        fetchStudent(arg1?.toInt()!!)
        subscribeToStudentAdded(arg1.toInt())
    }

    private fun subscribeToStudentAdded(collegeId: Int) {
        viewModelScope.launch {
            subscribeToStudentAddedUseCase.execute(collegeId)
                .collect { student ->
                    _data.value += listOf(student)
                }
        }
    }

    private fun fetchStudent(collegeId: Int) {
        if (isLoading.value) return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getStudentByCollegeIdUseCase.execute(collegeId)

                _data.value += result

            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}
