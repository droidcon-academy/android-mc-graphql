package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.CollegeRequestEntity
import javax.inject.Inject

class DeleteCollegeUseCase @Inject constructor (
    private val graphQLClient: IGraphQLClient
) {

    suspend fun execute(collegeId: Int):Boolean{
        return graphQLClient.deleteCollege(collegeId)
    }
}