package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import javax.inject.Inject

class CreateCollegeUseCase @Inject constructor (
    private val graphQLClient: IGraphQLClient
) {

    suspend fun execute(collegeRequestDTO: CollegeRequestDTO):CollegeEntity?{
        return graphQLClient.addCollege(collegeRequestDTO)
    }
}