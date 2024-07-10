package com.droidcon.graphqlmaster.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.data.dto.StudentRequestDTO
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import com.droidcon.graphqlmaster.domain.usecase.CreateCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.CreateStudentUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetStudentByCollegeIdUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetStudentUseCase
import com.droidcon.graphqlmaster.domain.usecase.SubscribeToStudentAddedUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
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
    savedStateHandle: SavedStateHandle,
    private val getStudentUseCase: GetStudentUseCase,
    private val getStudentByCollegeIdUseCase: GetStudentByCollegeIdUseCase,
    private val subscribeToStudentAddedUseCase: SubscribeToStudentAddedUseCase,
): ViewModel() {


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val _data = MutableStateFlow<List<StudentEntity>>(emptyList())
    val data: StateFlow<List<StudentEntity>> = _data

    val collegeId = MutableStateFlow<Int>(1)
    init {
        val arg1: String? = savedStateHandle["collegeId"]
        fetchStudent(arg1?.toInt()!!)
        subscribeToStudentAdded(arg1.toInt())
    }

    fun subscribeToStudentAdded(collegeId: Int) {
        viewModelScope.launch {
            subscribeToStudentAddedUseCase.execute(collegeId)
                .collect { student ->
                    _data.value += listOf(student)
                }
        }
    }

    fun fetchStudent(collegeId: Int) {
        if (isLoading.value) return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = if(collegeId == 0) {
                    getStudentUseCase.execute()
                } else {
                    getStudentByCollegeIdUseCase.execute(collegeId)
                }

                _data.value += result

            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}
