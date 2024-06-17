package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.data.dto.StudentRequestDTO
import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import javax.inject.Inject

class CreateStudentUseCase @Inject constructor (
    private val graphQLClient: IGraphQLClient
) {

    suspend fun execute(studentRequestDTO: StudentRequestDTO):StudentEntity{
        return graphQLClient.addStudent(studentRequestDTO)
    }
}