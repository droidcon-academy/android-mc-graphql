package com.droidcon.graphqlmaster.domain

import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.data.dto.StudentRequestDTO
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.PaginationCollegeEntity
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import kotlinx.coroutines.flow.Flow

interface IGraphQLClient {
    suspend fun getColleges():List<CollegeEntity>
    suspend fun getPaginationColleges(limit: Int, Skip:Int): PaginationCollegeEntity?
    suspend fun getStudents(collegeId:Int):List<StudentEntity>

    suspend fun addStudent(studentRequestDTO: StudentRequestDTO):StudentEntity
    suspend fun addCollege(collegeRequestDTO: CollegeRequestDTO):CollegeEntity

    fun subscribeToStudentAdded(collegeId: Int): Flow<StudentEntity>
}