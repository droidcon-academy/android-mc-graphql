package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.PaginationCollegeEntity
import javax.inject.Inject

class GetPaginationCollegeUseCase @Inject constructor (
    private val graphQLClient: IGraphQLClient
) {

    suspend fun execute(limit: Int, skip:Int):PaginationCollegeEntity? {
        return graphQLClient.getPaginationColleges(limit, skip)
    }
}