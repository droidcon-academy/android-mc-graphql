package com.droidcon.graphqlmaster.domain

import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.CollegeRequestEntity
import com.droidcon.graphqlmaster.domain.model.PaginationCollegeEntity
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import kotlinx.coroutines.flow.Flow

interface IGraphQLClient {
    suspend fun getCollegeByCollegeId(collegeId:Int):CollegeEntity?
    suspend fun getColleges():List<CollegeEntity>
    suspend fun getFragmentStudentByCollegeId(collegeId:Int):CollegeEntity?
    suspend fun addCollege(collegeRequestEntity: CollegeRequestEntity):CollegeEntity?
    suspend fun updateCollege(collegeRequestEntity: CollegeRequestEntity):CollegeEntity?
    suspend fun subscribeToCollege(collegeId: Int): Flow<StudentEntity>
    suspend fun deleteCollege(collegeId: Int):Boolean
    suspend fun getPaginationColleges(limit: Int, skip:Int): PaginationCollegeEntity?
}