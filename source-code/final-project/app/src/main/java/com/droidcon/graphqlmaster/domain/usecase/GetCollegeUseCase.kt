package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import javax.inject.Inject

class GetCollegeUseCase @Inject constructor (
    private val graphQLClient: IGraphQLClient
) {

    suspend fun execute():List<CollegeEntity>{
        return graphQLClient.getColleges().sortedBy { it.id }
    }
}