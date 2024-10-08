package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.CollegeRequestEntity
import javax.inject.Inject

class CreateCollegeUseCase @Inject constructor (
    private val graphQLClient: IGraphQLClient
) {

    suspend fun execute(collegeRequestEntity: CollegeRequestEntity):CollegeEntity?{
        return graphQLClient.addCollege(collegeRequestEntity)
    }
}