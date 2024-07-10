package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import javax.inject.Inject

class GetStudentUseCase @Inject constructor (
    private val graphQLClient: IGraphQLClient
) {
    suspend fun execute():List<StudentEntity>{
        return graphQLClient.getStudents().sortedBy { it.id }
    }
}