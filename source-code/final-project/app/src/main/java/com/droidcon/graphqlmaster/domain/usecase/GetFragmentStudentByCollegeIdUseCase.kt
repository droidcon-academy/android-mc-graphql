package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import javax.inject.Inject

class GetFragmentStudentByCollegeIdUseCase @Inject constructor (
    private val graphQLClient: IGraphQLClient
) {

    suspend fun execute(collegeId:Int):CollegeEntity?{
        return graphQLClient.getFragmentStudentByCollegeId(collegeId)
    }
}