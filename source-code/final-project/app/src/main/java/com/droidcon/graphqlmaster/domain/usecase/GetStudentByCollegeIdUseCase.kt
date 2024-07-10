package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import javax.inject.Inject

class GetStudentByCollegeIdUseCase @Inject constructor (
    private val graphQLClient: IGraphQLClient
) {

    suspend fun execute(collegeId: Int):List<StudentEntity>{
        return graphQLClient.getStudentByCollegeId(collegeId).sortedBy { it.id }
    }
}